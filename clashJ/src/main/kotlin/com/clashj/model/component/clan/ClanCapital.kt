package com.clashj.model.component.clan

/**
 * Represents a Clash of Clans API ClanCapital.
 *
 * @property capitalHallLevel The hall level of the clan's capital.
 * @property districts The list of [District]s belonging to the capital.
 */
data class ClanCapital(
    val capitalHallLevel: Int,
    val districts: List<District>
)

/**
 * Represents a district belonging to a clan's capital.
 *
 * @property name The name of the district.
 * @property id The ID of the district.
 * @property districtHallLevel The hall level of the district.
 */
data class District(
    val name: String,
    val id: Int,
    val districtHallLevel: Int
)
