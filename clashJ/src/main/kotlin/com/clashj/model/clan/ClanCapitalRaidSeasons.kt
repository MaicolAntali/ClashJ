package com.clashj.model.clan

import com.clashj.model.common.Badge
import com.clashj.model.common.Paging

data class ClanCapitalRaidSeasons(
    val items: List<ClanCapitalRaidSeason>,
    val paging: Paging?
)

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

data class ClanCapitalRaidSeasonAttackLogEntry(
    val defender: ClanCapitalRaidSeasonClanInfo,
    val attackCount: Int,
    val districtCount: Int,
    val districtsDestroyed: Int,
    val districts: List<ClanCapitalRaidSeasonDistrict>
)

data class ClanCapitalRaidSeasonDefenceLogEntry(
    val attacker: ClanCapitalRaidSeasonClanInfo,
    val attackCount: Int,
    val districtCount: Int,
    val districtsDestroyed: Int,
    val districts: List<ClanCapitalRaidSeasonDistrict>
)

data class ClanCapitalRaidSeasonClanInfo(
    val tag: String,
    val name: String,
    val level: Int,
    val badgeUrls: Badge,
)

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

data class ClanCapitalRaidSeasonAttack(
    val attacker: ClanCapitalRaidSeasonAttacker,
    val destructionPercent: Int,
    val stars: Int
)

data class ClanCapitalRaidSeasonAttacker(
    val tag: String,
    val name: String
)

data class ClanCapitalRaidSeasonMember(
    val tag: String,
    val name: String,
    val attacks: Int,
    val attackLimit: Int,
    val bonusAttackLimit: Int,
    val capitalResourcesLooted: Int
)

