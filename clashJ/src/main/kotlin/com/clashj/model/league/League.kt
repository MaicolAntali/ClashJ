package com.clashj.model.league

import com.clashj.model.common.Icon

/**
 * Represents the *League* model of the Clash of Clans API.
 *
 * @property name The name of the league.
 * @property id The unique identifier for the league.
 * @property iconUrls The [Icon] URLs representing the league's icon in different sizes.
 */
data class League(
    val name: String,
    val id: Int,
    val iconUrls: Icon
)
