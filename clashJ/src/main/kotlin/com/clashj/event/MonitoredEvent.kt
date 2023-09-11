package com.clashj.event

import com.clashj.model.player.Player

sealed class MonitoredEvent<T> {
    abstract fun hasChanged(cached: T, current: T): Boolean

    sealed class PlayerEvents : MonitoredEvent<Player>() {
    }
}