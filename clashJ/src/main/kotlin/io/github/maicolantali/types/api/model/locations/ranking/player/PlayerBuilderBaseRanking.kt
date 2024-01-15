package io.github.maicolantali.types.api.model.locations.ranking.player

import io.github.maicolantali.types.api.model.leagues.simpleleague.SimpleLeague

/**
 * Represents the *PlayerBuilderBaseRanking* model of the Clash of Clans API.
 * The PlayerBuilderBaseRanking data class stores information about a player's ranking based on their builder base performance.
 *
 * @property clan Information about the player's clan and its ranking.
 * @property builderBaseLeague Information about the player's builder base league.
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
    val tag: String,
    val name: String,
    val expLevel: Int,
    val rank: Int,
    val previousRank: Int,
    val builderBaseTrophies: Int,
)
