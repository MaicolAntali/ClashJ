package com.clashj.event

import com.clashj.model.player.Player

/**
 * A sealed class representing monitored events with generic data type [T].
 *
 * This sealed class is designed for defining monitored events and their associated change detection logic.
 * It allows the creation of specific event types, each with its own change detection implementation.
 *
 * @param T The generic type representing the data associated with the monitored events.
 */
sealed class MonitoredEvent<T> {

    /**
     * Abstract function to determine whether the monitored event has changed based on cached and current data.
     *
     * @param cached The cached data of type [T].
     * @param current The current data of type [T].
     * @return `true` if the event has changed, `false` otherwise.
     */
    abstract fun hasChanged(cached: T, current: T): Boolean

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
            override fun hasChanged(cached: Player, current: Player): Boolean {
                return cached.donations < current.donations
            }
        }

        /**
         * Represents a player event related to [received donations][Player.donationsReceived].
         *
         * This event is fired when there is a change in the number of donations received by a player.
         */
        data object DonationsReceive : PlayerEvents() {
            override fun hasChanged(cached: Player, current: Player): Boolean {
                return cached.donationsReceived < current.donationsReceived
            }
        }
    }
}
