package com.clashj.event

import com.clashj.model.clan.Clan
import com.clashj.model.player.Player

/**
 * A sealed class representing callback functions for events with different types.
 *
 * This sealed class is used to define callback functions for events, each with a specific type.
 * It has two data subclasses: [PlayerCallback] and [ClanCallback], each representing a callback
 * for a specific event type.
 */
sealed class EventCallback {

    /**
     * Represents a callback for events involving player objects.
     *
     * @property callback A suspend function taking two [Player] objects as parameters.
     * It is invoked when a player-related event occurs.
     */
    data class PlayerCallback(val callback: suspend (Player, Player) -> Unit) : EventCallback()

    /**
     * Represents a callback for events involving clan objects.
     *
     * @property callback A suspend function taking two [Clan] objects as parameters.
     * It is invoked when a clan-related event occurs.
     */
    data class ClanCallback(val callback: suspend (Clan, Clan) -> Unit) : EventCallback()
}
