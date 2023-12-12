package io.github.maicolantali.types.internal.configuration

import ch.qos.logback.classic.Level
import io.github.maicolantali.types.internal.HttpClientLogLevel

/**
 * Data class representing the configuration for logging in an application.
 *
 * @property clientLogLevel The log level for general client-side logging.
 * @property httClientLogLevel The log level for HTTP client-specific logging.
 */
data class LoggingConfiguration(
    var clientLogLevel: Level = Level.INFO,
    var httClientLogLevel: HttpClientLogLevel = HttpClientLogLevel.NONE,
)
