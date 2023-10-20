package com.clashj.event


import java.time.LocalDateTime

/**
 * Sealed class representing specific maintenance-related monitored events.
 */
sealed class MaintenanceEvents : Event<LocalDateTime, LocalDateTime, LocalDateTime, Nothing>() {

    /**
     * Event fires when server maintenance begins.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerMaintenanceCallback(MonitoredEvent.MaintenanceEvents.MaintenanceStarted) { startTime ->
     *     // ...
     * }
     * ```
     */
    data object MaintenanceStared : MaintenanceEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: LocalDateTime,
            currentData: LocalDateTime,
            callback: Callback<LocalDateTime, LocalDateTime, Nothing>
        ) {
            if (cachedData.isEqual(currentData)) {
                callback.singleArg?.invoke(cachedData)
            }
        }
    }

    /**
     * Event fires when server maintenance is completed.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerMaintenanceCallback(MonitoredEvent.MaintenanceEvents.MaintenanceCompleted) { startTime, endTime ->
     *     // ...
     * }
     * ```
     */
    data object MaintenanceCompleted : MaintenanceEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: LocalDateTime,
            currentData: LocalDateTime,
            callback: Callback<LocalDateTime, LocalDateTime, Nothing>
        ) {
            if (!cachedData.isEqual(currentData)) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }
}
