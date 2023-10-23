package com.clashj.model.player

import com.clashj.model.common.Paging
import com.clashj.model.league.SimpleLeague

/**
 * Represents the *PlayerBuilderBaseRankingList* model of the Clash of Clans API.
 * The PlayerBuilderBaseRankingList data class stores a list of player rankings based on their builder base performance.
 *
 * @property items A list of player rankings based on their builder base performance.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class PlayerBuilderBaseRankingList(
    val items: List<PlayerBuilderBaseRanking>,
    val paging: Paging?,
)

/**
 * Represents the *PlayerBuilderBaseRanking* model of the Clash of Clans API.
 * The PlayerBuilderBaseRanking data class stores information about a player's ranking based on their builder base performance.
 *
 * @property clan Information about the player's clan and its ranking.
 * @property builderBaseLeague Information about the player's builder base league.
 * @property versusTrophies The current number of versus trophies earned by the player in the builder base.
 * @property versusBattleWins The total number of versus battle wins achieved by the player in the builder base.
 * @property tag The tag of the player.
 * @property name The name of the player.
 * @property expLevel The experience level of the player.
 * @property rank The current rank of the player in the builder base rankings.
 * @property previousRank The previous rank of the player in the builder base rankings.
 * @property builderBaseTrophies The current number of builder base trophies earned by the player.
 */
data class PlayerBuilderBaseRanking(
    val clan: PlayerRankingClan,
    val builderBaseLeague: SimpleLeague,
    val versusTrophies: Int,
    val versusBattleWins: Int,
    val tag: String,
    val name: String,
    val expLevel: Int,
    val rank: Int,
    val previousRank: Int,
    val builderBaseTrophies: Int,
)
