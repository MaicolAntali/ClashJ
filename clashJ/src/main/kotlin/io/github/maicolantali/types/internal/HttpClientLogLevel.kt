package io.github.maicolantali.types.internal

import io.ktor.client.plugins.logging.LogLevel

/**
 * Enumeration representing different log levels for an HTTP client.
 *
 * This enum class defines log levels that can be associated with an HTTP client,
 * mapping them to the corresponding log levels in the Ktor HTTP client library.
 */
enum class HttpClientLogLevel {
    /**
     * No logging.
     */
    NONE,

    /**
     * Log only informational messages for HTTP requests and responses.
     */
    INFO,

    /**
     * Log HTTP request and response bodies in addition to informational messages.
     */
    BODY,

    /**
     * Log HTTP headers in addition to informational messages.
     */
    HEADERS,

    /**
     * Log all details, including HTTP headers and request/response bodies.
     */
    ALL,

    ;

    /**
     * Converts the enum value to the corresponding Ktor log level.
     *
     * @return The Ktor log level associated with the enum value.
     */
    internal fun toKtorLogLevel() =
        when (this) {
            NONE -> LogLevel.NONE
            INFO -> LogLevel.INFO
            BODY -> LogLevel.BODY
            HEADERS -> LogLevel.HEADERS
            ALL -> LogLevel.ALL
        }
}
