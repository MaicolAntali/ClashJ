package io.github.maicolantali.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger

internal var mu.KLogger.level: Level
    get() = (underlyingLogger as Logger).level
    set(value) {
        (underlyingLogger as Logger).level = value
    }
