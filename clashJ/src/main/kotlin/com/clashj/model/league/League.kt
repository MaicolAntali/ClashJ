package com.clashj.model.league

import com.clashj.model.common.Icon

/**
 * Represents a Clash of Clans API League.
 *
 * @param name The name of the league.
 * @param id The ID of the league.
 * @param iconUrls The [Icon] object containing icon URLs for different sizes.
 */
data class League(
    val name: String,
    val id: Int,
    val iconUrls: Icon
)
