package io.github.maicolantali.types.api.model.players.playerhouse

/**
 * Represents the *PlayerHouse* model of the Clash of Clans API.
 *
 * @property elements A list of [HouseElements][HouseElement] that make up the player's house.
 */
data class PlayerHouse(
    val elements: List<HouseElement>,
)

