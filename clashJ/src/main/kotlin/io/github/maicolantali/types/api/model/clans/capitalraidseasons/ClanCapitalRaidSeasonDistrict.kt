package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeasonDistrict* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonDistrict data class stores information about a district in the Clan Capital Raid season.
 *
 * @property stars The total number of stars earned in the district.
 * @property name The name of the district.
 * @property id The unique identifier of the district.
 * @property destructionPercent The percentage of destruction achieved in the district.
 * @property attackCount The total number of attacks made in the district.
 * @property totalLooted The total amount of resources looted in the district.
 * @property attacks A list of [ClanCapitalRaidSeasonAttack] objects representing the attacks made in the district.
 * @property districtHallLevel The level of the district's hall.
 */
data class ClanCapitalRaidSeasonDistrict(
    val stars: Int,
    val name: String,
    val id: Int,
    val destructionPercent: Int,
    val attackCount: Int,
    val totalLooted: Int,
    val attacks: List<ClanCapitalRaidSeasonAttack>,
    val districtHallLevel: Int,
)
