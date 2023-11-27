package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeason* model of the Clash of Clans API.
 * The ClanCapitalRaidSeason data class stores information about an individual Clan Capital Raid season.
 *
 * @property attackLog A list of [ClanCapitalRaidSeasonAttackLogEntry] objects representing the attack log for the season.
 * @property defenseLog A list of [ClanCapitalRaidSeasonDefenceLogEntry] objects representing the defense log for the season.
 * @property state The state of the Clan Capital Raid season.
 * @property startTime The start time of the Clan Capital Raid season.
 * @property endTime The end time of the Clan Capital Raid season.
 * @property capitalTotalLoot The total amount of capital resources looted during the season.
 * @property raidsCompleted The total number of raids completed during the season.
 * @property totalAttacks The total number of attacks made during the season.
 * @property enemyDistrictsDestroyed The total number of enemy districts destroyed during the season.
 * @property offensiveReward The offensive reward earned during the season.
 * @property defensiveReward The defensive reward earned during the season.
 * @property members A list of [ClanCapitalRaidSeasonMember] objects representing the members participating in the season.
 */
data class ClanCapitalRaidSeason(
    val attackLog: List<ClanCapitalRaidSeasonAttackLogEntry>,
    val defenseLog: List<ClanCapitalRaidSeasonDefenceLogEntry>,
    val state: String,
    val startTime: String,
    val endTime: String,
    val capitalTotalLoot: Int,
    val raidsCompleted: Int,
    val totalAttacks: Int,
    val enemyDistrictsDestroyed: Int,
    val offensiveReward: Int,
    val defensiveReward: Int,
    val members: List<ClanCapitalRaidSeasonMember>,
)
