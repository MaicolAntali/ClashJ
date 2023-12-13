package io.github.maicolantali.util

import io.github.maicolantali.Client
import io.github.maicolantali.http.RequestHandler
import io.github.maicolantali.http.option.EngineOptions
import io.github.maicolantali.http.option.KeyOptions
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

internal fun Client.getConfiguredRequestHandler() =
    with(config) {
        RequestHandler(
            Credential(email, password),
            KeyOptions(key.keyName, key.keyDescription, key.keyCount),
            EngineOptions(httpClient.connectionTimeout, httpClient.requestTimeout),
            httpClient.engine,
            throttler,
        )
    }

/**
 * Retrieves a coroutine dispatcher configured based on the client's thread configuration.
 *
 * @return A coroutine dispatcher configured with the specified number of threads.
 */
internal fun Client.getConfiguredDispatcher() = Executors.newFixedThreadPool(config.nThread).asCoroutineDispatcher()
