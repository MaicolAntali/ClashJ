package io.github.maicolantali.http.option

/**
 * Represents the engine options for the Ktor client.
 *
 * @property connectionTimeout The connection timeout duration in milliseconds. Defaults to 15,000 milliseconds (15 seconds) if not specified.
 * @property requestTimeout The request timeout duration in milliseconds. Defaults to 15,000 milliseconds (15 seconds) if not specified.
 */
data class EngineOptions(
    val connectionTimeout: Long = 15_000,
    val requestTimeout: Long = 15_000,
)
