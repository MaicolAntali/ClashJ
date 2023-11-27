package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeasonDefenceLogEntry* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonDefenceLogEntry data class stores information about a defense log entry in the Clan Capital Raid season.
 *
 * @property attacker The information about the attacker clan participating in the defense log entry.
 * @property attackCount The total number of attacks made by the attacker clan in the defense log entry.
 * @property districtCount The total number of districts in the defense log entry.
 * @property districtsDestroyed The number of districts destroyed by the attacker clan in the defense log entry.
 * @property districts A list of [ClanCapitalRaidSeasonDistrict] objects representing the districts in the defense log entry.
 */
data class ClanCapitalRaidSeasonDefenceLogEntry(
    val attacker: ClanCapitalRaidSeasonClanInfo,
    val attackCount: Int,
    val districtCount: Int,
    val districtsDestroyed: Int,
    val districts: List<ClanCapitalRaidSeasonDistrict>,
)
