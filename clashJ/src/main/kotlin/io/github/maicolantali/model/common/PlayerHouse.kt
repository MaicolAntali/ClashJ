package io.github.maicolantali.model.common

/**
 * Represents the *PlayerHouse* model of the Clash of Clans API.
 *
 * @property elements A list of [HouseElements][HouseElement] that make up the player's house.
 */
data class PlayerHouse(
    val elements: List<HouseElement>,
)

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
