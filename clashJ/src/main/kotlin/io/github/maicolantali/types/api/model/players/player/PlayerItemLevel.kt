package io.github.maicolantali.types.api.model.players.player

import io.github.maicolantali.types.api.enums.Village

/**
 * Represents the *PlayerItemLevel* model of the Clash of Clans API.
 * The PlayerItemLevel data class stores information about an item level of a player in the game.
 *
 * @property level The current level of the item.
 * @property name The name of the item.
 * @property maxLevel The maximum level that the item can reach.
 * @property village The type of village associated with the item (Home Village or Builder Base).
 * @property superTroopIsActive A flag indicating whether a super troop is active for the item.
 * @property equipment A list of [PlayerItemLevel] that represents equipments.
 */
data class PlayerItemLevel(
    val level: Int,
    val name: String,
    val maxLevel: Int,
    val village: Village,
    val superTroopIsActive: Boolean,
    val equipment: List<PlayerItemLevel>?,
)
