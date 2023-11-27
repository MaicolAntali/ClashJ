package io.github.maicolantali.types.api.model.locations.ranking.player

import io.github.maicolantali.types.api.model.common.Badge

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
    val badgeUrls: Badge,
)
