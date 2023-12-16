package io.github.maicolantali.http

import ch.qos.logback.classic.Level
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.github.maicolantali.exception.BadGatewayException
import io.github.maicolantali.exception.ClashJException
import io.github.maicolantali.exception.HttpException
import io.github.maicolantali.exception.InvalidCredentialException
import io.github.maicolantali.exception.MaintenanceException
import io.github.maicolantali.exception.NotFoundException
import io.github.maicolantali.http.option.EngineOptions
import io.github.maicolantali.http.option.KeyOptions
import io.github.maicolantali.http.option.RequestOptions
import io.github.maicolantali.http.response.CreateKeyResponse
import io.github.maicolantali.http.response.KeyListResponse
import io.github.maicolantali.http.response.base.Key
import io.github.maicolantali.http.throttler.BaseThrottler
import io.github.maicolantali.util.API_BASE_URL
import io.github.maicolantali.util.Credential
import io.github.maicolantali.util.DEV_SITE_BASE_URL
import io.github.maicolantali.util.SafeSet
import io.github.maicolantali.util.setLevel
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Base64
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Handles HTTP requests to and from [DEV_SITE_BASE_URL] and [API_BASE_URL].
 *
 * @param credential The `Credential` object containing the email and password for authentication.
 * @param keyOptions The `KeyOptions` specifying the key name, optional description, and key count.
 * @param engineOptions The `EngineOptions` specifying the connection timeout and request timeout.
 * @param engine The `HttpClientEngine` to be used for HTTP requests.
 * @param throttler The `BaseThrottler` instance responsible for controlling the rate of API requests.
 */
class RequestHandler(
    private val credential: Credential,
    private val keyOptions: KeyOptions,
    private val engineOptions: EngineOptions,
    private val engine: HttpClientEngine,
    private val throttler: BaseThrottler,
) {
    private companion object {
        private const val MAX_KEY_COUNT = 10
    }

    private val keysSet = SafeSet<String>()
    private val isLoginInProgress = AtomicBoolean(false)
    private val logger = KotlinLogging.logger {}

    init {
        logger.setLevel(Level.TRACE)
    }

    private val httpClient =
        HttpClient(engine) {
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
     * Performs the login to the developer site using the provided email and password.
     *
     * This function authenticates the user with the developer website, retrieves temporary API keys,
     * and filters the keys based on the current IP address.
     * It also creates new keys, if necessary, to ensure the desired number of API keys per account is met.
     *
     * @throws InvalidCredentialException if the provided login credentials are invalid or incorrect.
     */
    suspend fun login() =
        coroutineScope {
            logger.info { "Initiating login process to the developer website." }

            keysSet.clear()

            lateinit var loginResponse: HttpResponse
            var delayMillis = 100L

            // Retry the login operation up to three times with exponential backoff
            for (attempt in 1..3) {
                try {
                    loginResponse =
                        httpClient.post("$DEV_SITE_BASE_URL/login") {
                            setBody(credential)
                        }
                    break // Success, exit the loop
                } catch (e: HttpRequestTimeoutException) {
                    if (attempt == 3) {
                        logger.error(e) { "Login operation failed after multiple attempts. Error: ${e.message}" }
                        throw e
                    }

                    logger.warn { "Login operation failed. Retrying in $delayMillis ms." }
                    delay(delayMillis)
                    delayMillis *= 2 // Exponential backoff
                }
            }

            if (loginResponse.status == HttpStatusCode.Forbidden) {
                logger.error { "Login failed. The provided credentials are incorrect or invalid." }
                closeHttpClient()
                throw InvalidCredentialException("The provided credentials are incorrect or invalid.")
            }
            logger.info { "Login successful." }

            val ip: String =
                async {
                    logger.trace { "Retrieving the current IP address." }
                    getIp(loginResponse.body<JsonObject>().getAsJsonPrimitive("temporaryAPIToken").asString)
                }.await()

            logger.info { "Successfully retrieved the current IP address. IP address: $ip" }
            retriveKeys(ip, loginResponse.headers["set-cookie"]!!)
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
     * @throws ClashJException if the response can't be handled, or if the maximum retry attempts are reached without a valid response.
     */
    internal suspend inline fun <reified T> request(
        url: String,
        requestOptions: RequestOptions = RequestOptions(),
    ): T {
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
     * @throws NotFoundException If the resource wasn't found (HTTP status code 404).
     * @throws BadGatewayException If there was an issue with the API (HTTP status code 500, 502, 504).
     * @throws ClashJException If there is an issue that can't be handled specifically.
     */
    private suspend inline fun <reified T> executeRequest(
        url: String,
        requestOptions: RequestOptions,
    ): T =
        coroutineScope {
            logger.trace { "Executing request to: $url" }

            for (tries in 0..requestOptions.maxRetryAttempts) {
                try {
                    val response =
                        httpClient.request(url) {
                            method = requestOptions.method
                            setBody(requestOptions.body)
                            headers.append(HttpHeaders.Authorization, "Bearer ${keysSet.next()}")
                        }

                    when (response.status) {
                        HttpStatusCode.OK -> {
                            logger.trace { "Request successful to: $url" }
                            return@coroutineScope response.body<T>()
                        }

                        HttpStatusCode.Forbidden -> {
                            val responseJson = response.body<JsonObject>()

                            if (responseJson.get("reason").asString == "accessDenied.invalidIp") {
                                if (!isLoginInProgress.get()) {
                                    isLoginInProgress.set(true)
                                    login()
                                    isLoginInProgress.set(false)
                                } else {
                                    delay(engineOptions.requestTimeout * (tries + 1))
                                }

                                continue
                            } else {
                                logger.error { "Forbidden for: $url, Response: $responseJson, Reason: ${responseJson.get("reason")}" }
                            }

                            throw HttpException("Forbidden for: $url, Response: $responseJson, Reason: ${responseJson.get("reason")}")
                        }

                        HttpStatusCode.ServiceUnavailable -> {
                            logger.error { "API is in maintenance for: $url" }
                            throw MaintenanceException("The API is in maintenance for: $url")
                        }

                        HttpStatusCode.TooManyRequests -> {
                            logger.error { "Reached maximum rate-limits for: $url. Consider adjusting the rate limits." }
                            throw HttpException("Reached maximum rate-limits for: $url. Consider adjusting the rate limits.")
                        }

                        HttpStatusCode.NotFound -> {
                            val errorMsg = "Not Found (404) for: $url. Response: ${response.body<String>()}"
                            logger.error { errorMsg }
                            throw NotFoundException(errorMsg)
                        }

                        HttpStatusCode.InternalServerError, HttpStatusCode.BadGateway, HttpStatusCode.GatewayTimeout -> {
                            val errorMsg = "Error (${response.status}) for: $url. Response: ${response.body<String>()}"
                            logger.error { errorMsg }
                            throw BadGatewayException(errorMsg)
                        }

                        else -> {
                            val errorMsg = "Unable to handle this response for: $url. Response: ${response.body<String>()}"
                            logger.error { errorMsg }
                            throw ClashJException(errorMsg)
                        }
                    }
                } catch (e: HttpRequestTimeoutException) {
                    if (tries > 2) {
                        logger.error(e) { "API timed out waiting for the request: $url" }
                        throw BadGatewayException("The API timed out waiting for the request: $url")
                    }

                    logger.warn(e) { "HttpRequestTimeoutException caught. Retrying to execute the request: $url." }
                    delay(tries * 3 + 1L)
                    continue
                }
            }

            logger.error { "Reached the maxRetryAttempts (${requestOptions.maxRetryAttempts}) without valid responses for: $url" }
            throw ClashJException("Reached the maxRetryAttempts (${requestOptions.maxRetryAttempts}) without valid responses for: $url")
        }

    /**
     * Performs the retrieval of keys based on the current IP address.
     *
     * Retrieves the list of keys from the developer site and filters them based on the current IP address.
     * Revokes keys that don't match the current IP and appends keys that match the current IP to the [keysSet].
     * Also, creates new keys within the limit of 10 keys per account until the desired keyCount is reached.
     *
     * @param currentIp The current IP address.
     * @param cookies The cookies obtained during the login process.
     * @throws ClashJException if no existing keys match the specified key name or the desired number of keys couldn't be generated.
     */
    private suspend fun retriveKeys(
        currentIp: String,
        cookies: String,
    ) = coroutineScope {
        logger.trace { "Initiating key retrieval process from the developer site." }

        val keyListResponse: KeyListResponse =
            httpClient.post("$DEV_SITE_BASE_URL/apikey/list") {
                headers.append(HttpHeaders.Cookie, cookies)
            }.body()

        // Filter keys for the specified key name,
        val keys = keyListResponse.keys.toMutableList()
        logger.info { "${keys.size} valid keys retrieved from the developer site." }

        // Revoke keys that do not have the current IP
        keys.filter { it.name == keyOptions.keyName }
            .filter { !it.cidrRanges.contains(currentIp) }
            .map { key ->
                launch {
                    if (revokeKey(key, cookies)) {
                        keysSet.remove(key.id)
                        keys.remove(key)
                        logger.info { "Revoked key '${key.key}' as it does not match the current IP address." }
                    }
                }
            }.joinAll()

        // Append keys that have the current IP
        keys.filter { it.name == keyOptions.keyName }
            .filter { it.cidrRanges.contains(currentIp) }
            .takeWhile { keysSet.size() < keyOptions.keyCount }
            .map { key ->
                launch {
                    keysSet.add(key.key)
                    logger.info { "Added key '${key.key}' to the key set as it matches the current IP address." }
                }
            }.joinAll()

        // Create new keys within limits of 10 keys per account
        while (keysSet.size() < keyOptions.keyCount && keys.size < MAX_KEY_COUNT) {
            val newKey = createKey(currentIp, cookies)
            keysSet.add(newKey.key)
            keys.add(newKey)
            logger.info { "Created a new key '${newKey.key}' and added it to the key set." }
        }

        if (keysSet.size() < keyOptions.keyCount && keys.size == 10) {
            val warnMsg =
                "Required ${keyOptions.keyCount} keys, but maximum ${keysSet.size()} found/made (limit: 10/acc). " +
                    "Please delete keys or decrease `keyCount`."
            logger.warn { warnMsg }
        }

        if (keysSet.size() == 0) {
            val errorMessage =
                "${keys.size} existing API keys, none match keyName: ${keyOptions.keyName} " +
                    "Specify another keyName or delete unused keys at 'https://developer.clashofclans.com'."
            logger.error { errorMessage }
            throw ClashJException(errorMessage)
        }

        logger.trace { "Key retrieval and processing completed successfully." }
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
    private suspend fun createKey(
        ip: String,
        cookie: String,
    ): Key {
        val keyJson =
            """{"cidrRanges": ["$ip"],"name": "${keyOptions.keyName}","description": "${
                if (!keyOptions.keyDescription.isNullOrBlank()) {
                    keyOptions.keyDescription
                } else {
                    LocalDateTime.now()
                }
            }"}"""

        logger.info { "Generating a new API key with the following data: $keyJson" }

        val response: CreateKeyResponse =
            httpClient.post("$DEV_SITE_BASE_URL/apikey/create") {
                setBody(keyJson)
                headers.append(HttpHeaders.Cookie, cookie)
            }.body()

        if (response.status.message != "ok") {
            closeHttpClient()
            val errorMessage = "Failed to create a new API key. Details: ${response.status.detail}"
            logger.error { errorMessage }
            throw HttpException(errorMessage)
        }

        logger.info { "Successfully created a new API key: ${response.key.key}" }
        logger.trace { "API key creation process completed successfully." }
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
    private suspend fun revokeKey(
        key: Key,
        cookie: String,
    ): Boolean {
        logger.trace { "Initiating revocation process for the key named: ${key.name} with IP: ${key.cidrRanges}" }
        logger.info { "Revoking the key named: ${key.name} with IP: ${key.cidrRanges} (as it does not match our current IP address)." }

        val response =
            httpClient.post("${DEV_SITE_BASE_URL}/apikey/revoke") {
                setBody("""{"id": "${key.id}"}""")
                headers.append(HttpHeaders.Cookie, cookie)
            }

        if (response.status == HttpStatusCode.OK) {
            logger.info { "Successfully revoked the key: ${key.name} with IP: ${key.cidrRanges}." }
        } else {
            logger.warn { "Failed to revoke the key: ${key.name} with IP: ${key.cidrRanges}. HTTP Status: ${response.status}" }
        }

        logger.trace { "Revocation process for the key named: ${key.name} with IP: ${key.cidrRanges} completed." }
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
        logger.trace { "Decoding token and extracting IP address." }

        val decodedJson = JsonParser.parseString(String(Base64.getDecoder().decode(token.split('.')[1]))).asJsonObject

        val cidrs =
            decodedJson.getAsJsonArray("limits")
                .find { it.isJsonObject && it.asJsonObject.has("cidrs") }?.asJsonObject?.getAsJsonArray("cidrs")

        val ipAddress = cidrs?.firstOrNull()?.asString?.split('/')?.firstOrNull()

        if (ipAddress != null) {
            logger.info { "Successfully extracted IP address: $ipAddress from the token." }
        } else {
            logger.error { "Failed to extract IP address from the token. CIDRs not found or invalid format." }
        }

        logger.trace { "Token decoding and IP extraction process completed." }
        return ipAddress ?: throw IllegalStateException("Failed to extract IP address from the token.")
    }

    /**
     * Closes the HTTP client and releases any resources associated with it.
     */
    private fun closeHttpClient() = httpClient.close()
}
