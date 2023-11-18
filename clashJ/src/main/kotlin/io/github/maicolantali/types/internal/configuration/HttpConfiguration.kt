package io.github.maicolantali.types.internal.configuration

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache5.Apache5

/**
 * Configuration class for HTTP settings.
 *
 * @property engine The HTTP client engine to be used. Default is Apache5.
 * @property connectionTimeout The maximum time, in milliseconds, to wait for a connection to be established.
 *                             Default is 15,000 (15 seconds).
 * @property requestTimeout The maximum time, in milliseconds, to wait for a request to complete.
 *                          Default is 15,000 (15 seconds).
 */
data class HttpConfiguration(
    var engine: HttpClientEngine = Apache5.create(),
    var connectionTimeout: Long = 15_000,
    var requestTimeout: Long = 15_000,
)
