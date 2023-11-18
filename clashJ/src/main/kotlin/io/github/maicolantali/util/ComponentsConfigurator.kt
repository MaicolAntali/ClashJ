package io.github.maicolantali.util

import io.github.maicolantali.Client
import io.github.maicolantali.http.RequestHandler
import io.github.maicolantali.http.option.EngineOptions
import io.github.maicolantali.http.option.KeyOptions

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
