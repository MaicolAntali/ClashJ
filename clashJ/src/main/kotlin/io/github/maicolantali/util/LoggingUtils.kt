package io.github.maicolantali.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import io.github.oshai.kotlinlogging.DelegatingKLogger
import io.github.oshai.kotlinlogging.KLogger

/**
 * Sets the log level of the underlying logger to the specified [Level].
 *
 * @param level The desired log level.
 */
internal fun KLogger.setLevel(level: Level) {
    val underlyingLogger = (this as DelegatingKLogger<*>).underlyingLogger

    (underlyingLogger as Logger).level = level
}
