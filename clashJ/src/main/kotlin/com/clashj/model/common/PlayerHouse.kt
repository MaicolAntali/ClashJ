package com.clashj.model.common

/**
 * Represents a Clash of Clans API PlayerHouse.
 *
 * @param elements The list of [HouseElement] objects representing elements that build the house.
 */
data class PlayerHouse(
    val elements: List<HouseElement>
)

/**
 * Represents a Clash of Clans API HouseElement.
 *
 * @param type The type of the house element.
 * @param id The ID of the house element.
 */
data class HouseElement(
    val type: String,
    val id: Int
)