package com.clashj.event

import com.clashj.model.player.Player

sealed class MonitoredEvent<T> {
    abstract fun hasChanged(cached: T, current: T): Boolean

    sealed class PlayerEvents : MonitoredEvent<Player>() {
        data object Donations : PlayerEvents() {
            override fun hasChanged(cached: Player, current: Player): Boolean {
                return cached.donations < current.donations
            }
        }

        data object DonationsReceive : PlayerEvents() {
            override fun hasChanged(cached: Player, current: Player): Boolean {
                return cached.donationsReceived < current.donationsReceived
            }
        }
    }
}