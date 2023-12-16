package io.github.maicolantali.types.internal.configuration

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

/**
 * Configuration data class for HTTP client settings.
 *
 * @property engine The HTTP client engine to use. Defaults to [CIO.create].
 * @property socketTimeoutMillis The socket timeout in milliseconds. Defaults to 5,000 milliseconds.
 * @property connectionTimeout The connection timeout in milliseconds. Defaults to 5,000 milliseconds.
 * @property requestTimeout The request timeout in milliseconds. Defaults to 10,000 milliseconds.
 */
data class HttpConfiguration(
    var engine: HttpClientEngine = CIO.create(),
    var socketTimeoutMillis: Long = 5_000,
    var connectionTimeout: Long = 5_000,
    var requestTimeout: Long = 10_000,
)
