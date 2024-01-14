package io.github.maicolantali.types.api.model.players.player

/**
 * Represents the *PlayerLegendStatistics* model of the Clash of Clans API.
 * The PlayerLegendStatistics data class stores information about a player's legend statistics in the game.
 *
 * @property legendTrophies The number of legend trophies earned by the player.
 * @property previousBuilderBaseSeason Information about the player's performance in the previous builder base season in the legend league tournament.
 * @property currentSeason Information about the player's current season performance in the legend league tournament.
 * @property previousVersusSeason Information about the player's performance in the previous versus season in the legend league tournament.
 * @property bestBuilderBaseSeason Information about the player's best performance in a builder base season in the legend league tournament.
 * @property previousSeason Information about the player's performance in the previous season in the legend league tournament.
 * @property bestSeason Information about the player's best performance in a season in the legend league tournament.
 */
data class PlayerLegendStatistics(
    val legendTrophies: Int,
    val previousBuilderBaseSeason: PlayerLegendLeagueTournamentSeasonResult,
    val currentSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousVersusSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestBuilderBaseSeason: PlayerLegendLeagueTournamentSeasonResult,
    val previousSeason: PlayerLegendLeagueTournamentSeasonResult,
    val bestSeason: PlayerLegendLeagueTournamentSeasonResult,
)
