package com.clashj.model.player

import com.clashj.model.clan.component.ClanMemberRole
import com.clashj.model.common.Badge
import com.clashj.model.label.Label
import com.clashj.model.common.PlayerHouse
import com.clashj.model.league.League
import com.clashj.model.league.SimpleLeague
import com.google.gson.annotations.SerializedName

data class Player(
    val clan: PlayerClan,
    val league: League,
    val builderBaseLeague: SimpleLeague,
    val role: ClanMemberRole,
    val warPreference: PlayerWarPreference,
    val attackWins: Int,
    val defenseWins: Int,
    val versusTrophies: Int,
    val bestVersusTrophies: Int,
    val townHallLevel: Int,
    val townHallWeaponLevel: Int,
    val versusBattleWins: Int,
    val legendStatistics: PlayerLegendStatistics,
    val troops: List<PlayerItemLevel>,
    val heroes: List<PlayerItemLevel>,
    val spells: List<PlayerItemLevel>,
    val labels: List<Label>,
    val tag: String,
    val name: String,
    val expLevel: Int,
    val trophies: Int,
    val bestTrophies: Int,
    val donations: Int,
    val donationsReceived: Int,
    val builderHallLevel: Int,
    val builderBaseTrophies: Int,
    val bestBuilderBaseTrophies: Int,
    val warStars: Int,
    val achievements: List<PlayerAchievementsProgress>,
    val clanCapitalContributions: Int,
    val playerHouse: PlayerHouse
)

data class PlayerClan(
    val tag: String,
    val clanLevel: Int,
    val name: String,
    val badgeUrls: Badge
)

enum class PlayerWarPreference {
    @SerializedName("in")
    IN,

    @SerializedName("out")
    OUT;
}

data class PlayerLegendStatistics(
    val legendTrophies: Int,
    val previousBuilderBaseSeaso: PlayerLegendLeagueTournamentSeasonResult,
    val currentSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousVersusSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestBuilderBaseSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestVersusSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestSeason: PlayerLegendLeagueTournamentSeasonResult
)

data class PlayerLegendLeagueTournamentSeasonResult(
    val trophies: Int,
    val id: String,
    val rank: Int
)

data class PlayerItemLevel(
    val level: Int,
    val name: String,
    val maxLevel: Int,
    val village: Village,
    val superTroopIsActive: Boolean
)

data class PlayerAchievementsProgress(
    val stars: Int,
    val value: Int,
    val name: String,
    val target: Int,
    val info: String,
    val completionInfo: String,
    val village: Village,
)

enum class Village {
    @SerializedName("home")
    HOME_VILLAGE,

    @SerializedName("builderBase")
    BUILDER_BASE;
}
