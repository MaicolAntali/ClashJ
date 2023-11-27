package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeasonAttackLogEntry* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonAttackLogEntry data class stores information about an attack log entry in the Clan Capital Raid season.
 *
 * @property defender The information about the defender clan participating in the attack log entry.
 * @property attackCount The total number of attacks made by the defender clan in the attack log entry.
 * @property districtCount The total number of districts in the attack log entry.
 * @property districtsDestroyed The number of districts destroyed by the defender clan in the attack log entry.
 * @property districts A list of [ClanCapitalRaidSeasonDistrict] objects representing the districts in the attack log entry.
 */
data class ClanCapitalRaidSeasonAttackLogEntry(
    val defender: ClanCapitalRaidSeasonClanInfo,
    val attackCount: Int,
    val districtCount: Int,
    val districtsDestroyed: Int,
    val districts: List<ClanCapitalRaidSeasonDistrict>,
)
