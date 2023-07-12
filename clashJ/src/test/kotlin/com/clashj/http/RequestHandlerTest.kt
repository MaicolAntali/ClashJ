package com.clashj.http

import com.clashj.exception.ClashJException
import com.clashj.exception.InvalidCredentialException
import com.clashj.util.Credential
import com.clashj.util.DEV_SITE_BASE_URL
import com.clashj.util.option.EngineOptions
import com.clashj.util.option.KeyOptions
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RequestHandlerTest {

    @Test
    fun `should throw an InvalidCredentialException`() {
        runBlocking {
            val mockEngine = MockEngine { _ ->
                respondError(HttpStatusCode.Forbidden)
            }

            val requestHandler = RequestHandler(
                Credential("email", "password"),
                KeyOptions("name", null, 0),
                EngineOptions(),
                mockEngine
            )

            assertThrows<InvalidCredentialException> { requestHandler.login() }
        }
    }

    @Test
    fun `should throw an ClashJException - Exist too many keys`() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                when (request.url.toString()) {
                    "$DEV_SITE_BASE_URL/login" -> {
                        respond(
                            content = """{temporaryAPIToken:".eyAibGltaXRzIjogWyB7ICJ0aWVyIjogImRldmVsb3Blci9icm9uemUiLCAidHlwZSI6ICJ0aHJvdHRsaW5nIiB9LCB7ICJjaWRycyI6IFsgIjEyLjM1LjU2Ny44OS8zMiIgXSwgInR5cGUiOiAiY2xpZW50IiB9LCB7ICJvcmlnaW5zIjogWyAiZGV2ZWxvcGVyLmNsYXNob2ZjbGFucy5jb20iIF0sICJ0eXBlIjogImNvcnMiIH0gXSB9"}""",
                            status = HttpStatusCode.OK,
                            headers = headers {
                                append("set-cookie", "{session=123}")
                                append(HttpHeaders.ContentType, "application/json")
                            }
                        )
                    }

                    "$DEV_SITE_BASE_URL/apikey/list" -> {
                        respond(
                            content = """{"status":{"code":0,"message":"ok","detail":null},"keys":[{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"},{"id":"e4a528aa-0c84-41e4-8fbc-f82df421b3b7","name":"mockedKeyName","description":"mockedKeyDesc","cidrRanges":["123.45.56.89"],"key":"123key456"}]}""",
                            status = HttpStatusCode.OK,
                            headers = headers {
                                append(HttpHeaders.ContentType, "application/json")
                            }
                        )
                    }


                    else -> {
                        respondBadRequest()
                    }
                }
            }

            val requestHandler = RequestHandler(
                Credential("email", "password"),
                KeyOptions("name", null, 0),
                EngineOptions(),
                mockEngine
            )

            assertThrows<ClashJException> { requestHandler.login() }
        }
    }
}
