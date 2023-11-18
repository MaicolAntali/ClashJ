package io.github.maicolantali.types.internal.configuration

/**
 * Configuration class for event handling settings.
 *
 * @property nThread The number of threads for event handling. Default is 4.
 * @property pollingInterval The interval, in milliseconds, for polling events. Default is 15,000 (15 seconds).
 * @property maintenanceCheckInterval The interval, in milliseconds, for maintenance checks. Default is 30,000 (30 seconds).
 */
data class EventConfiguration(
    var nThread: Int = 4,
    var pollingInterval: Long = 15_000,
    var maintenanceCheckInterval: Long = 30_000,
)
