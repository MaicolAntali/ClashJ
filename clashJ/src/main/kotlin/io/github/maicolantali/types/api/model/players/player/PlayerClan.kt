package io.github.maicolantali.types.api.model.players.player

import io.github.maicolantali.types.api.model.common.Badge

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
