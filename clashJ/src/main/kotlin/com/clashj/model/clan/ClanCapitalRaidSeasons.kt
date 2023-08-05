package com.clashj.model.clan

import com.clashj.model.common.Badge
import com.clashj.model.common.Paging

/**
 * Represents the *ClanCapitalRaidSeasons* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasons data class stores information about Clan Capital Raid seasons.
 *
 * @property items A list of [ClanCapitalRaidSeason] objects representing individual Clan Capital Raid seasons.
 * @property paging Information about the paging of the Clan Capital Raid seasons.
 */
data class ClanCapitalRaidSeasons(
    val items: List<ClanCapitalRaidSeason>,
    val paging: Paging?
)

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
    val members: List<ClanCapitalRaidSeasonMember>
)

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
    val districts: List<ClanCapitalRaidSeasonDistrict>
)

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
    val districts: List<ClanCapitalRaidSeasonDistrict>
)

/**
 * Represents the *ClanCapitalRaidSeasonClanInfo* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonClanInfo data class stores information about a clan
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property level The level of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
 */
data class ClanCapitalRaidSeasonClanInfo(
    val tag: String,
    val name: String,
    val level: Int,
    val badgeUrls: Badge,
)

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
    val districtHallLevel: Int
)

/**
 * Represents the *ClanCapitalRaidSeasonAttack* model of the Clash of Clans API.
 *
 * @property attacker The information about the attacker participating in the attack.
 * @property destructionPercent The percentage of destruction achieved in the attack.
 * @property stars The number of stars earned in the attack.
 */
data class ClanCapitalRaidSeasonAttack(
    val attacker: ClanCapitalRaidSeasonAttacker,
    val destructionPercent: Int,
    val stars: Int
)

/**
 * Represents the *ClanCapitalRaidSeasonAttacker* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonAttacker data class stores information about an attacker
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the attacker.
 * @property name The name of the attacker.
 */
data class ClanCapitalRaidSeasonAttacker(
    val tag: String,
    val name: String
)

/**
 * Represents the *ClanCapitalRaidSeasonMember* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonMember data class stores information about a member
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the member.
 * @property name The name of the member.
 * @property attacks The number of attacks made by the member during the season.
 * @property attackLimit The maximum number of regular attacks allowed for the member during the season.
 * @property bonusAttackLimit The maximum number of bonus attacks allowed for the member during the season.
 * @property capitalResourcesLooted The total amount of capital resources looted by the member during the season.
 */
data class ClanCapitalRaidSeasonMember(
    val tag: String,
    val name: String,
    val attacks: Int,
    val attackLimit: Int,
    val bonusAttackLimit: Int,
    val capitalResourcesLooted: Int
)

