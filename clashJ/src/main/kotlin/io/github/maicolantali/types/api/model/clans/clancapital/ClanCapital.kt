package io.github.maicolantali.types.api.model.clans.clancapital

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
