package com.clashj.http

import com.clashj.exception.BadGatewayException
import com.clashj.exception.ClashJException
import com.clashj.exception.HttpException
import com.clashj.exception.InvalidCredentialException
import com.clashj.exception.MaintenanceException
import com.clashj.http.option.EngineOptions
import com.clashj.http.option.KeyOptions
import com.clashj.http.option.RequestOptions
import com.clashj.http.throttler.BatchThrottler
import com.clashj.util.Credential
import com.clashj.util.DEV_SITE_BASE_URL
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

class RequestHandlerTest {
    @Nested
    inner class Login {
        @Test
        fun `should throw an InvalidCredentialException`() {
            runBlocking {
                val mockEngine =
                    MockEngine { _ ->
                        respondError(HttpStatusCode.Forbidden)
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(),
                        mockEngine,
                        BatchThrottler(),
                    )

                assertThrows<InvalidCredentialException> { requestHandler.login() }
            }
        }

        @Test
        fun `should throw an ClashJException - Exist too many keys`() {
            runBlocking {
                val mockEngine =
                    MockEngine { request ->
                        when (request.url.toString()) {
                            "$DEV_SITE_BASE_URL/login" -> {
                                respond(
                                    content = File("src/test/resources/json/tooManyKeysLogin.json").readText(),
                                    status = HttpStatusCode.OK,
                                    headers =
                                        headers {
                                            append("set-cookie", "{session=123}")
                                            append(HttpHeaders.ContentType, "application/json")
                                        },
                                )
                            }

                            "$DEV_SITE_BASE_URL/apikey/list" -> {
                                respond(
                                    content = File("src/test/resources/json/tooManyKeysList.json").readText(),
                                    status = HttpStatusCode.OK,
                                    headers =
                                        headers {
                                            append(HttpHeaders.ContentType, "application/json")
                                        },
                                )
                            }

                            else -> {
                                respondBadRequest()
                            }
                        }
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(),
                        mockEngine,
                        BatchThrottler(),
                    )

                assertThrows<ClashJException> { requestHandler.login() }
            }
        }
    }

    @Nested
    inner class Request {
        @Test
        fun `should throw an ClashJException`() {
            runBlocking {
                val mockEngine =
                    MockEngine { _ ->
                        // Http error didn't handle by the `request` fun
                        respondError(HttpStatusCode.NotImplemented)
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(),
                        mockEngine,
                        BatchThrottler(),
                    )

                val e = assertThrows<ClashJException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("Not able to handle this response")
            }
        }

        @Test
        fun `should throw a BadGatewayException - Caused by RequestTimeoutException`() {
            runBlocking {
                val mockEngine =
                    MockEngine { _ ->
                        throw HttpRequestTimeoutException("url", 1000)
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(requestTimeout = 1000),
                        mockEngine,
                        BatchThrottler(),
                    )

                val e = assertThrows<BadGatewayException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("The API timed out waiting for the request")
            }
        }

        @Test
        fun `should throw a HttpException - Caused by TooManyRequests`() {
            runBlocking {
                val mockEngine =
                    MockEngine { _ ->
                        respondError(HttpStatusCode.TooManyRequests)
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(),
                        mockEngine,
                        BatchThrottler(),
                    )

                val e = assertThrows<HttpException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("Reached the maximum rate-limits by the API")
            }
        }

        @Test
        fun `should throw a MaintenanceException`() {
            runBlocking {
                val mockEngine =
                    MockEngine { _ ->
                        respondError(HttpStatusCode.ServiceUnavailable)
                    }

                val requestHandler =
                    RequestHandler(
                        Credential("email", "password"),
                        KeyOptions("name", null, 0),
                        EngineOptions(),
                        mockEngine,
                        BatchThrottler(),
                    )

                val e = assertThrows<MaintenanceException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("The API is in maintenance")
            }
        }
    }
}
