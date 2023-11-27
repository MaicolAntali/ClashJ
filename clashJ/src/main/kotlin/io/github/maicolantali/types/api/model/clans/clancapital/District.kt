package io.github.maicolantali.types.api.model.clans.clancapital

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
