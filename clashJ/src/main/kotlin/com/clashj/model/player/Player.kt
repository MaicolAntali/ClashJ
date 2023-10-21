package com.clashj.model.player

import com.clashj.model.clan.component.ClanMemberRole
import com.clashj.model.common.Badge
import com.clashj.model.common.PlayerHouse
import com.clashj.model.label.Label
import com.clashj.model.league.League
import com.clashj.model.league.SimpleLeague
import com.google.gson.annotations.SerializedName

/**
 * Represents the *Player* model of the Clash of Clans API.
 * The Player data class stores comprehensive information about a player in the game.
 *
 * @property clan Information about the player's clan.
 * @property league Information about the player's league.
 * @property builderBaseLeague Information about the player's builder base league.
 * @property role The role of the player in their clan (e.g., Member, Leader, etc.).
 * @property warPreference The war preference status of the player (In or Out).
 * @property attackWins The total number of attack wins achieved by the player.
 * @property defenseWins The total number of defense wins achieved by the player.
 * @property versusTrophies The current number of versus trophies earned by the player.
 * @property bestVersusTrophies The highest number of versus trophies earned by the player.
 * @property townHallLevel The level of the player's town hall.
 * @property townHallWeaponLevel The level of the player's town hall weapon (if available).
 * @property versusBattleWins The total number of versus battle wins achieved by the player.
 * @property legendStatistics Information about the player's legend league statistics.
 * @property troops A list of player's troops and their levels.
 * @property heroes A list of player's heroes and their levels.
 * @property spells A list of player's spells and their levels.
 * @property labels A list of labels associated with the player.
 * @property tag The tag of the player.
 * @property name The name of the player.
 * @property expLevel The experience level of the player.
 * @property trophies The current number of trophies earned by the player.
 * @property bestTrophies The highest number of trophies ever earned by the player.
 * @property donations The total number of troops donated by the player.
 * @property donationsReceived The total number of troops received by the player as donations.
 * @property builderHallLevel The level of the player's builder hall.
 * @property builderBaseTrophies The current number of builder base trophies earned by the player.
 * @property bestBuilderBaseTrophies The highest number of builder base trophies earned by the player.
 * @property warStars The total number of war stars earned by the player.
 * @property achievements A list of player's achievements and their progress.
 * @property clanCapitalContributions The total number of contributions made by the player to the clan capital.
 * @property playerHouse Information about the player's house elements.
 */
data class Player(
    val clan: PlayerClan?,
    val league: League?,
    val builderBaseLeague: SimpleLeague,
    val role: ClanMemberRole?,
    val warPreference: PlayerWarPreference?,
    val attackWins: Int,
    val defenseWins: Int,
    val versusTrophies: Int,
    val bestVersusTrophies: Int,
    val townHallLevel: Int,
    val townHallWeaponLevel: Int,
    val versusBattleWins: Int,
    val legendStatistics: PlayerLegendStatistics?,
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
    val playerHouse: PlayerHouse,
)

/**
 * Represents the *PlayerClan* model of the Clash of Clans API.
 * The PlayerClan data class stores information about a player's clan.
 *
 * @property tag The tag of the clan.
 * @property clanLevel The level of the clan.
 * @property name The name of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes (small, medium, and large).
 */
data class PlayerClan(
    val tag: String,
    val clanLevel: Int,
    val name: String,
    val badgeUrls: Badge,
)

/**
 * Represents the *PlayerWarPreference* model of the Clash of Clans API.
 * The PlayerWarPreference enum class defines a player's war preference status.
 */
enum class PlayerWarPreference {
    /**
     * The player is participating in wars.
     */
    @SerializedName("in")
    IN,

    /**
     * The player is not participating in wars.
     */
    @SerializedName("out")
    OUT,
}

/**
 * Represents the *PlayerLegendStatistics* model of the Clash of Clans API.
 * The PlayerLegendStatistics data class stores information about a player's legend statistics in the game.
 *
 * @property legendTrophies The number of legend trophies earned by the player.
 * @property previousBuilderBaseSeason Information about the player's performance in the previous builder base season in the legend league tournament.
 * @property currentSeason Information about the player's current season performance in the legend league tournament.
 * @property previousVersusSeason Information about the player's performance in the previous versus season in the legend league tournament.
 * @property bestBuilderBaseSeason Information about the player's best performance in a builder base season in the legend league tournament.
 * @property bestVersusSeason Information about the player's best performance in a versus season in the legend league tournament.
 * @property previousSeason Information about the player's performance in the previous season in the legend league tournament.
 * @property bestSeason Information about the player's best performance in a season in the legend league tournament.
 */
data class PlayerLegendStatistics(
    val legendTrophies: Int,
    val previousBuilderBaseSeason: PlayerLegendLeagueTournamentSeasonResult,
    val currentSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousVersusSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestBuilderBaseSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestVersusSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestSeason: PlayerLegendLeagueTournamentSeasonResult,
)

/**
 * Represents the *PlayerLegendLeagueTournamentSeasonResult* model of the Clash of Clans API.
 * The PlayerLegendLeagueTournamentSeasonResult data class stores information about a player's performance in a legend league tournament season.
 *
 * @property trophies The number of trophies earned by the player in the season.
 * @property id The unique identifier of the season.
 * @property rank The rank achieved by the player in the season.
 */
data class PlayerLegendLeagueTournamentSeasonResult(
    val trophies: Int,
    val id: String,
    val rank: Int,
)

/**
 * Represents the *PlayerItemLevel* model of the Clash of Clans API.
 * The PlayerItemLevel data class stores information about an item level of a player in the game.
 *
 * @property level The current level of the item.
 * @property name The name of the item.
 * @property maxLevel The maximum level that the item can reach.
 * @property village The type of village associated with the item (Home Village or Builder Base).
 * @property superTroopIsActive A flag indicating whether a super troop is active for the item.
 */
data class PlayerItemLevel(
    val level: Int,
    val name: String,
    val maxLevel: Int,
    val village: Village,
    val superTroopIsActive: Boolean,
)

/**
 * Represents the *PlayerAchievementsProgress* model of the Clash of Clans API.
 * The PlayerAchievementsProgress data class stores information about the progress of a player's achievements in the game.
 *
 * @property stars The current number of stars earned for the achievement.
 * @property value The current value of the achievement progress.
 * @property name The name of the achievement.
 * @property target The target value that needs to be reached for completing the achievement.
 * @property info Additional information about the achievement.
 * @property completionInfo Information about the completion status of the achievement.
 * @property village The type of village associated with the achievement (Home Village or Builder Base).
 */
data class PlayerAchievementsProgress(
    val stars: Int,
    val value: Int,
    val name: String,
    val target: Int,
    val info: String,
    val completionInfo: String,
    val village: Village,
)

/**
 * Represents the *Village* model of the Clash of Clans API.
 * The Village enum class defines the type of village: Home Village or Builder Base.
 */
enum class Village {
    /**
     * The Home Village.
     */
    @SerializedName("home")
    HOME_VILLAGE,

    /**
     * The Builder Base.
     */
    @SerializedName("builderBase")
    BUILDER_BASE,
}
