package com.clashj.model.clan.component

/**
 * Represents the *ClanCapital* model of the Clash of Clans API.
 * The ClanCapital data class stores information about a clan's capital and its districts.
 *
 * @property capitalHallLevel The hall level of the clan's capital.
 * @property districts A list of [District] objects containing information about the capital's districts.
 */
data class ClanCapital(
    val capitalHallLevel: Int,
    val districts: List<District>?,
)

/**
 * Represents the *District* model of the Clash of Clans API.
 * The District data class stores information about a district within a clan's capital.
 *
 * @property name The name of the district.
 * @property id The unique identifier for the district.
 * @property districtHallLevel The hall level of the district.
 */
data class District(
    val name: String,
    val id: Int,
    val districtHallLevel: Int,
)
