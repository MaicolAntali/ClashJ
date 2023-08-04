package com.clashj.http

import com.clashj.exception.BadGatewayException
import com.clashj.exception.ClashJException
import com.clashj.exception.HttpException
import com.clashj.exception.InvalidCredentialException
import com.clashj.exception.MaintenanceException
import com.clashj.exception.NotFoundException
import com.clashj.http.option.EngineOptions
import com.clashj.http.option.KeyOptions
import com.clashj.http.option.RequestOptions
import com.clashj.http.response.CreateKeyResponse
import com.clashj.http.response.KeyListResponse
import com.clashj.http.response.base.Key
import com.clashj.http.throttler.BaseThrottler
import com.clashj.http.throttler.BatchThrottler
import com.clashj.util.API_BASE_URL
import com.clashj.util.Credential
import com.clashj.util.DEV_SITE_BASE_URL
import com.clashj.util.SafeSet
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache5.Apache5
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.Base64

/**
 * Handles HTTP requests from/to [DEV_SITE_BASE_URL] and [API_BASE_URL].
 *
 * @param credential The credential object containing the email and password for authentication.
 *
 * @param keyOptions The key options specifying the key name, optional description, and key count.
 *
 * @param engineOptions The engine options specifying the connection timeout and request timeout.
 * @param engine The HttpClientEngine to be used for HTTP requests. Defaults to Apache5.create() if not provided.
 */
class RequestHandler(
    private val credential: Credential,

    private val keyOptions: KeyOptions,

    private val engineOptions: EngineOptions,
    private val engine: HttpClientEngine = Apache5.create(),

    private val throttler: BaseThrottler = BatchThrottler()
) {

    companion object {
        private const val MAX_KEY_COUNT = 10
        private val log = LoggerFactory.getLogger(RequestHandler::class.java)
    }

    private val keysSet = SafeSet<String>()

    private val httpClient = HttpClient(engine) {
        install(HttpTimeout) {
            requestTimeoutMillis = engineOptions.requestTimeout
            connectTimeoutMillis = engineOptions.requestTimeout
        }
        install(ContentNegotiation) { gson() }
        defaultRequest {
            // Set for all requests the content type to: application/json
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * Performs the login in to the developer site using the provided email and password.
     *
     * Retrieves temporary API keys from the login response and filters the keys
     * based on the current IP address. It also creates new keys within the limit of 10 keys per account.
     *
     * @throws InvalidCredentialException if invalid login credentials were used during the login process.
     */
    suspend fun login() = withContext(Dispatchers.IO) {
        log.info("Logging into the developer website.")

        keysSet.clear()

        val loginResponse = httpClient.post("$DEV_SITE_BASE_URL/login") {
            setBody(credential)
        }

        if (loginResponse.status == HttpStatusCode.Forbidden) {
            log.error("The provided credential are incorrect or invalid.")
            closeHttpClient()
            throw InvalidCredentialException("The provided credential are incorrect or invalid.")
        }
        log.info("Successfully logged in.")

        val ip: String = async {
            getIp(loginResponse.body<JsonObject>().getAsJsonPrimitive("temporaryAPIToken").asString)
        }.await()

        log.info("The current IP address has been successfully retrieved. IP address: $ip")

        launch { retriveKeys(ip, loginResponse.headers["set-cookie"]!!) }
    }


    /**
     * Performs an HTTP request to the specified URL with the given [requestOptions].
     *
     * If `requestOptions.ignoreThrottler` is false (default behavior),
     * the method waits for throttling based on the configured [throttler].
     * If throttling is in effect,
     * the method delays the execution of the request until the throttling interval is met.
     *
     * @param url The URL to which the HTTP request will be sent.
     * @param requestOptions The options for customizing the HTTP request, such as method, body, and maximum retry attempts.
     * @return The response body of the HTTP request, deserialized into the specified type [T].
     *
     * @throws HttpException if the HTTP response status is other than [HttpStatusCode.OK], HttpStatusCode.Forbidden, HttpStatusCode.ServiceUnavailable, or HttpStatusCode.TooManyRequests.
     * @throws MaintenanceException if the HTTP response status is [HttpStatusCode.ServiceUnavailable], indicating the API is in maintenance.
     * @throws NotFoundException if the HTTP response status is [HttpStatusCode.NotFound].
     * @throws BadGatewayException if the HTTP response status is [HttpStatusCode.InternalServerError],¬
     * [HttpStatusCode.BadGateway], or [HttpStatusCode.GatewayTimeout].
     * @throws ClashJException if the response cannot be handled, or if the maximum retry attempts are reached without a valid response.
     */
    internal suspend inline fun <reified T> request(url: String, requestOptions: RequestOptions = RequestOptions()): T {
        if (!requestOptions.ignoreThrottler) {
            throttler.wait()
        }

        return executeRequest<T>(url, requestOptions)
    }


    /**
     * Executes an HTTP request with the given [url] and [requestOptions].
     *
     * The method will retry the request for `requestOptions.maxRetryAttempts` times in case of request timeouts.
     * The delay between retries increases with each attempt,
     * starting from 1 millisecond and increasing by 3 milliseconds for each subsequent retry.
     *
     * @param url The URL to which the HTTP request will be sent.
     * @param requestOptions The options specifying the request parameters.
     * @return The response body of the HTTP request, deserialized to the specified type [T].
     *
     * @throws HttpException If the HTTP request fails with a specific HTTP status code.
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the resource was not found (HTTP status code 404).
     * @throws BadGatewayException If there was an issue with the API (HTTP status code 500, 502, 504).
     * @throws ClashJException If there is an issue that cannot be handled specifically.
     */
    private suspend inline fun <reified T> executeRequest(url: String, requestOptions: RequestOptions): T =
        withContext(Dispatchers.IO) {
            for (tries in 0..requestOptions.maxRetryAttempts) {
                try {
                    val response = httpClient.request(url) {
                        method = requestOptions.method
                        setBody(requestOptions.body)
                        headers.append(HttpHeaders.Authorization, "Bearer ${keysSet.next()}")
                    }

                    when (response.status) {
                        HttpStatusCode.OK -> {
                            return@withContext response.body<T>()
                        }

                        HttpStatusCode.Forbidden -> {
                            val responseJson = response.body<JsonObject>()
                            log.info("Forbidden, resp: $responseJson, reason: ${responseJson.get("reason")}")

                            if (responseJson.get("reason").asString == "accessDenied.invalidIp") {
                                async { login() }.await()
                                continue
                            }

                            throw HttpException("Forbidden, resp: $responseJson, reason: ${responseJson.get("reason")}")
                        }

                        HttpStatusCode.ServiceUnavailable -> {
                            throw MaintenanceException("The API is in maintenance.")
                        }

                        HttpStatusCode.TooManyRequests -> {
                            log.error("Reached the maximum rate-limits by the API. Consider a new number of request allowed per second.")
                            throw HttpException("Reached the maximum rate-limits by the API. Consider a new number of request allowed per second.")
                        }

                        HttpStatusCode.NotFound -> {
                            throw NotFoundException("Not Found (404): ${response.body<String>()}")
                        }

                        HttpStatusCode.InternalServerError, HttpStatusCode.BadGateway, HttpStatusCode.GatewayTimeout -> {
                            throw BadGatewayException("Error (${response.status}): ${response.body<String>()}")
                        }

                        else -> {
                            throw ClashJException("Not able to handle this response: ${response.body<String>()}")
                        }
                    }
                } catch (e: HttpRequestTimeoutException) {
                    if (tries > 2) {
                        throw BadGatewayException("The API timed out waiting for the request.")
                    }

                    log.debug("HttpRequestTimeoutException caught. Retry to execute the request: $url.")
                    delay(tries * 3 + 1L)
                    continue
                }
            }

            log.error("Reached the maxRetryAttempts (${requestOptions.maxRetryAttempts}) without a valid responses.")
            throw ClashJException("Reached the maxRetryAttempts (${requestOptions.maxRetryAttempts}) without a valid responses.")
        }


    /**
     * Performs the retrieval of keys based on the current IP address.
     *
     * Retrieves the list of keys from the developer site and filters them based on the current IP address.
     * Revokes keys that do not match the current IP and appends keys that match the current IP to the [keysSet].
     * Also, creates new keys within the limit of 10 keys per account until the desired keyCount is reached.
     *
     * @param currentIp The current IP address.
     * @param cookies The cookies obtained during the login process.
     * @throws ClashJException if no existing keys match the specified key name or the desired number of keys couldn't be generated.
     */
    private suspend fun retriveKeys(currentIp: String, cookies: String) = coroutineScope {
        val keyListResponse: KeyListResponse = httpClient.post("$DEV_SITE_BASE_URL/apikey/list") {
            headers.append(HttpHeaders.Cookie, cookies)
        }.body()

        // Filter key for the specified key name,
        val keys = keyListResponse.keys.toMutableList()
        log.info("${keys.size} valid keys retrieved from the developer site.")

        // Revoke keys that have not the current ip
        keys.filter { it.name == keyOptions.keyName }
            .filter { !it.cidrRanges.contains(currentIp) }
            .map { key ->
                launch {
                    if (revokeKey(key, cookies)) {
                        keysSet.remove(key.id)
                        keys.remove(key)
                    }
                }
            }.joinAll()

        // Append keys that have the current ip
        keys.filter { it.name == keyOptions.keyName }
            .filter { it.cidrRanges.contains(currentIp) }
            .takeWhile { keysSet.size() < keyOptions.keyCount }
            .map { key ->
                launch {
                    keysSet.add(key.key)
                }
            }.joinAll()

        // Create new keys within limits of 10 keys per account
        while (keysSet.size() < keyOptions.keyCount && keys.size < MAX_KEY_COUNT) {
            val newKey = createKey(currentIp, cookies)
            keysSet.add(newKey.key)
            keys.add(newKey)
        }

        if (keysSet.size() < keyOptions.keyCount && keys.size == 10) {
            log.warn("Required ${keyOptions.keyCount} keys, but maximum ${keysSet.size()} found/made (limit: 10/acc). Please delete keys or decrease `keyCount`.")
        }

        if (keysSet.size() == 0) {
            log.error("${keys.size} existing API keys, none match keyName: ${keyOptions.keyName}. Specify another keyName or delete unused keys at 'https://developer.clashofclans.com'.")
            throw ClashJException("${keys.size} existing API keys, none match keyName: ${keyOptions.keyName}. Specify another keyName or delete unused keys at 'https://developer.clashofclans.com'.")
        }
    }

    /**
     * Creates a new key based on the provided IP address and cookie.
     *
     * Sends an HTTP request to the developer site to create the key and retrieve the created key object.
     *
     * @param ip The IP address associated with the key.
     * @param cookie The cookie obtained during the login process.
     * @return The created key object.
     *
     * @throws HttpException if the key creation fails.
     */
    private suspend fun createKey(ip: String, cookie: String): Key {
        val keyJson =
            """{"cidrRanges": ["$ip"],"name": "${keyOptions.keyName}","description": "${
                if (!keyOptions.keyDescription.isNullOrBlank()) {
                    keyOptions.keyDescription
                } else {
                    LocalDateTime.now()
                }
            }"}"""
        log.info("Generating a new key based on the data: $keyJson")

        val response: CreateKeyResponse = httpClient.post("$DEV_SITE_BASE_URL/apikey/create") {
            setBody(keyJson)
            headers.append(HttpHeaders.Cookie, cookie)
        }.body()

        if (response.status.message != "ok") {
            log.error("Failed to create the new API Key. Details: ${response.status.detail}")
            closeHttpClient()
            throw HttpException("Failed to create the new API Key. Details: ${response.status.detail}")
        }

        return response.key
    }

    /**
     * Revokes a key using the provided key object and cookie.
     *
     * Sends an HTTP request to the developer site to revoke the specified key.
     *
     * @param key The key object to be revoked.
     * @param cookie The cookie obtained during the login process.
     * @return `true` if the revocation is successful, `false` otherwise.
     */
    private suspend fun revokeKey(key: Key, cookie: String): Boolean {
        log.info("Removing the key named: ${key.name} and IP: ${key.cidrRanges} (as it does not match our current IP address).")

        val response = httpClient.post("${DEV_SITE_BASE_URL}/apikey/revoke") {
            setBody("""{"id": "${key.id}"}""")
            headers.append(HttpHeaders.Cookie, cookie)
        }

        return response.status == HttpStatusCode.OK
    }

    /**
     * Retrieves the IP address from the provided token.
     *
     * Decodes the base64 token and extracts the IP address associated with it.
     * The IP address is extracted from the `limits` object in the decoded token.
     *
     * @param token The token containing the encoded IP address.
     * @return The IP address extracted from the token.
     */
    private fun getIp(token: String): String {
        val decodedJson = JsonParser.parseString(String(Base64.getDecoder().decode(token.split('.')[1]))).asJsonObject

        val cidrs = decodedJson.getAsJsonArray("limits")
            .find { it.asJsonObject.has("cidrs") }!!.asJsonObject!!.getAsJsonArray("cidrs")

        return cidrs.first().asString.split('/').first()
    }

    private fun closeHttpClient() = httpClient.close()
}
