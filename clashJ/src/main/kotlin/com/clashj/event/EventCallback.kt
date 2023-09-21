package com.clashj.event

import com.clashj.model.clan.Clan
import com.clashj.model.player.Player

/**
 * A callback interface for handling events of different types.
 *
 * This interface defines callback functions for various event types.
 * It includes two nested interfaces:
 *  - [PlayerCallback] for player-related events.
 *  - [ClanCallback] for clan-related events.
 */
interface EventCallback {

    /**
     * Nested interface for player-related event callbacks.
     */
    interface PlayerCallback : EventCallback {
        /**
         * Represents a callback for events involving player objects.
         *
         * @property callback A suspend function taking two [Player] objects as parameters.
         */
        data class PlayerSimpleCallback(val callback: suspend (Player, Player) -> Unit) : PlayerCallback

        /**
         * Represents a callback for player-related events with an additional identifier.
         *
         * @property callback A suspend function taking two [Player] objects and an additional [String] identifier as parameters.
         */
        data class PlayerIterableCallback(val callback: suspend (Player, Player, String) -> Unit) : PlayerCallback
    }


    /**
     * Represents a callback for events involving clan objects.
     *
     * @property callback A suspend function taking two [Clan] objects as parameters.
     * It is invoked when a clan-related event occurs.
     */
    data class ClanCallback(val callback: suspend (Clan, Clan) -> Unit) : EventCallback
}
