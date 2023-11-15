package io.github.maicolantali.model.league

import io.github.maicolantali.model.common.Paging

/**
 * Represents the *LeagueSeasonList* model of the Clash of Clans API.
 *
 * @property items A list of [LeagueSeason] objects representing individual league seasons.
 * @property paging Information about the paging of the league season list.
 */
data class LeagueSeasonList(
    val items: List<LeagueSeason>,
    val paging: Paging?,
)

/**
 * Represents the *LeagueSeason* model of the Clash of Clans API.
 *
 * @property id The unique identifier of the league season.
 */
data class LeagueSeason(
    val id: String,
)
