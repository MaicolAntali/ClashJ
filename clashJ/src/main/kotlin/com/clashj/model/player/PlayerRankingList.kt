package com.clashj.model.player

import com.clashj.model.common.Badge
import com.clashj.model.common.Paging

/**
 * Represents the *PlayerRankingList* model of the Clash of Clans API.
 * The PlayerRankingList data class stores a list of player rankings.
 *
 * @property items A list of player rankings.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class PlayerRankingList(
    val items: List<PlayerRanking>,
    val paging: Paging?
)

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
    val trophies: Int
)

/**
 * Represents the *PlayerRankingClan* model of the Clash of Clans API.
 * The PlayerRankingClan data class stores information about a player's clan in the rankings.
 *
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes (small, medium, and large).
 */
data class PlayerRankingClan(
    val tag: String,
    val name: String,
    val badgeUrls: Badge
)
