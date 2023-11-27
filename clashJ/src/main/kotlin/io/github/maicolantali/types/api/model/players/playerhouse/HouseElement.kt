package io.github.maicolantali.types.api.model.players.playerhouse

/**
 * Represents the *HouseElement* model of the Clash of Clans API.
 * HouseElements are individual elements that make up a player's house in the capital.
 *
 * @property type The type of the house element.
 * @property id The unique identifier for the house element.
 */
data class HouseElement(
    val type: String,
    val id: Int,
)
