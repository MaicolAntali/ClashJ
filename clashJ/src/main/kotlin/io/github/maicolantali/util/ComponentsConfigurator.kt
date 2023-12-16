package io.github.maicolantali.util

import io.github.maicolantali.Client
import io.github.maicolantali.http.RequestHandler
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

internal fun Client.getConfiguredRequestHandler() = RequestHandler(Credential(email, password), config)

/**
 * Retrieves a coroutine dispatcher configured based on the client's thread configuration.
 *
 * @return A coroutine dispatcher configured with the specified number of threads.
 */
internal fun Client.getConfiguredDispatcher() = Executors.newFixedThreadPool(config.nThread).asCoroutineDispatcher()

internal fun RequestHandler.getConfiguredHttpClient() =
    HttpClient(config.httpClient.engine) {
        install(Logging) {
            level = config.logging.httClientLogLevel.toKtorLogLevel()
        }

        install(HttpTimeout) {
            socketTimeoutMillis = config.httpClient.socketTimeoutMillis
            connectTimeoutMillis = config.httpClient.connectionTimeout
            requestTimeoutMillis = config.httpClient.requestTimeout
        }

        install(ContentNegotiation) { gson() }
    }
