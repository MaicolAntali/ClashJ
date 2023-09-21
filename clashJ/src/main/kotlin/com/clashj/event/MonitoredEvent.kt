package com.clashj.event

import com.clashj.model.player.Player

/**
 * A sealed class representing monitored events with generic data type [T] and a callback type [C].
 *
 * This sealed class is designed to define monitored events and their associated change detection logic.
 * It allows the creation of specific event types, each with its own event firing implementation.
 *
 * @param T The generic type representing the data associated with the monitored events.
 * @param C The generic type representing the callback type associated with the monitored events.
 */
sealed class MonitoredEvent<T, C : EventCallback> {

    /**
     * Abstract function to fire the monitored event based on cached and current data.
     *
     * @param cached The cached data of type [T].
     * @param current The current data of type [T].
     * @param callback The callback of type [C] to be invoked when the event occurs.
     */
    abstract suspend fun fireCallback(cached: T, current: T, callback: C)

    internal suspend fun <T> executeCallback(cached: T, current: T, callback: C, vararg args: String) {
        when (callback) {
            is EventCallback.PlayerCallback.PlayerSimpleCallback -> {
                if (cached is Player && current is Player) {
                    callback.callback.invoke(cached, current)
                }
            }

            is EventCallback.PlayerCallback.PlayerIterableCallback -> {
                if (cached is Player && current is Player && args[0].isNotEmpty()) {
                    callback.callback.invoke(cached, current, args[0])
                }
            }

            is EventCallback.ClanCallback -> TODO()

        }
    }

    /**
     * Sealed class representing specific player-related monitored events.
     */
    sealed class PlayerEvents : MonitoredEvent<Player>() {

        /**
         * Represents a player event related to [donations][Player.donations].
         *
         * This event is fired when there is a change in the number of donations made by a player.
         */
        data object Donations : PlayerEvents() {
            override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
                if (cached.donations < current.donations) {
                    executeCallback(cached, current, callback)
                }
            }
        }

        /**
         * Event fired when a player receives troops
         */
        data object DonationsReceive : PlayerEvents() {
            override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
                if (cached.donationsReceived < current.donationsReceived) {
                    executeCallback(cached, current, callback)
                }
            }
        }
    }
}
