package io.github.maicolantali.http

import io.github.maicolantali.exception.BadGatewayException
import io.github.maicolantali.exception.ClashJException
import io.github.maicolantali.exception.HttpException
import io.github.maicolantali.exception.InvalidCredentialException
import io.github.maicolantali.exception.MaintenanceException
import io.github.maicolantali.http.option.RequestOptions
import io.github.maicolantali.types.internal.configuration.ClientConfiguration
import io.github.maicolantali.types.internal.configuration.HttpConfiguration
import io.github.maicolantali.util.Credential
import io.github.maicolantali.util.DEV_SITE_BASE_URL
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
                    getRequestHandlerMocked(mockEngine)

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

                val requestHandler = getRequestHandlerMocked(mockEngine)

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

                val requestHandler = getRequestHandlerMocked(mockEngine)

                val e = assertThrows<ClashJException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("Unable to handle this response")
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
                        ClientConfiguration(httpClient = HttpConfiguration(engine = mockEngine, requestTimeout = 1_000)),
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
                    getRequestHandlerMocked(mockEngine)

                val e = assertThrows<HttpException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("Reached maximum rate-limits")
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
                    getRequestHandlerMocked(mockEngine)

                val e = assertThrows<MaintenanceException> { requestHandler.request<String>("url", RequestOptions()) }
                assertThat(e).hasMessageContaining("The API is in maintenance")
            }
        }
    }

    private fun getRequestHandlerMocked(mockEngine: MockEngine) =
        RequestHandler(
            Credential("email", "password"),
            ClientConfiguration(httpClient = HttpConfiguration(engine = mockEngine)),
        )
}
