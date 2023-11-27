package io.github.maicolantali.types.api.model.locations.ranking.player

/**
 * Represents the *PlayerRanking* model of the Clash of Clans API.
 * The PlayerRanking data class stores information about a player's ranking.
 *
 * @property clan Information about the player's clan and its ranking.
 * @property attackWins The total number of attack wins achieved by the player.
 * @property defenseWins The total number of defense wins achieved by the player.
 * @property tag The tag of the player.
 * @property name The name of the player.
 * @property expLevel The experience level of the player.
 * @property rank The current rank of the player in the rankings.
 * @property previousRank The previous rank of the player in the rankings.
 * @property trophies The current number of trophies earned by the player.
 */
data class PlayerRanking(
    val clan: PlayerRankingClan,
    val attackWins: Int,
    val defenseWins: Int,
    val tag: String,
    val name: String,
    val expLevel: Int,
    val rank: Int,
    val previousRank: Int,
    val trophies: Int,
)
