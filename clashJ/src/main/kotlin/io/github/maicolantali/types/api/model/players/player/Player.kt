package io.github.maicolantali.types.api.model.players.player

import io.github.maicolantali.types.api.enums.ClanMemberRole
import io.github.maicolantali.types.api.enums.PlayerWarPreference
import io.github.maicolantali.types.api.model.labels.Label
import io.github.maicolantali.types.api.model.leagues.league.League
import io.github.maicolantali.types.api.model.leagues.simpleleague.SimpleLeague
import io.github.maicolantali.types.api.model.players.playerhouse.PlayerHouse

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
