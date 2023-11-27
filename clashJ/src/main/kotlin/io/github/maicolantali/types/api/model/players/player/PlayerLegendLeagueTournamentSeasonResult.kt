package io.github.maicolantali.types.api.model.players.player

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
