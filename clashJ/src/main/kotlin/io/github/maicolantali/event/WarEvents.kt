package io.github.maicolantali.event

import io.github.maicolantali.types.api.enums.WarState
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWar
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWarAttack

/**
 * Sealed class representing specific war-related monitored events.
 */
sealed class WarEvents : Event<ClanWar, ClanWar, ClanWarAttack, Nothing>() {
    /**
     * Event fires when a clan war is in the preparation state.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerWarCallback(MonitoredEvent.WarEvents.WarInPreparation) { war ->
     *     // ...
     * }
     * ```
     */
    data object WarInPreparation : WarEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: ClanWar,
            currentData: ClanWar,
            callback: Callback<ClanWar, ClanWarAttack, Nothing>,
        ) {
            if (currentData.state == WarState.PREPARATION && currentData.state != cachedData.state) {
                callback.singleArg?.invoke(cachedData)
            }
        }
    }

    /**
     * Event fires when a clan war has started.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerWarCallback(MonitoredEvent.WarEvents.WarStarted) { war ->
     *     // ...
     * }
     * ```
     */
    data object WarStarted : WarEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: ClanWar,
            currentData: ClanWar,
            callback: Callback<ClanWar, ClanWarAttack, Nothing>,
        ) {
            if (currentData.state == WarState.IN_WAR && currentData.state != cachedData.state) {
                callback.singleArg?.invoke(cachedData)
            }
        }
    }

    /**
     * Event fires when a clan war has ended.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerWarCallback(MonitoredEvent.WarEvents.WarEnded) { war ->
     *     // ...
     * }
     * ```
     */
    data object WarEnded : WarEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: ClanWar,
            currentData: ClanWar,
            callback: Callback<ClanWar, ClanWarAttack, Nothing>,
        ) {
            if (currentData.state == WarState.ENDED && currentData.state != cachedData.state) {
                callback.singleArg?.invoke(cachedData)
            }
        }
    }

    /**
     * Event fires when a new clan attack is performed in a clan war.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerWarCallback(MonitoredEvent.WarEvents.NewClanAttack) { war, attack ->
     *     // ...
     * }
     * ```
     */
    data object NewClanAttack : WarEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: ClanWar,
            currentData: ClanWar,
            callback: Callback<ClanWar, ClanWarAttack, Nothing>,
        ) {
            if (cachedData.getClanAttacks().isNotEmpty() && currentData.getClanAttacks().isNotEmpty()) {
                currentData.getClanAttacks()
                    .filter { it !in cachedData.getClanAttacks() }
                    .forEach {
                        callback.simple?.invoke(cachedData, it)
                    }
            }
        }
    }
}
