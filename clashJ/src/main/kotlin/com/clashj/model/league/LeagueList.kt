package com.clashj.model.league

import com.clashj.model.common.Paging

/**
 * Represents the *LeagueList* model of the Clash of Clans API.
 *
 * @property items A list of [League] objects representing individual leagues.
 * @property paging Information about the paging of the league list.
 */
data class LeagueList(
    val items: List<League>,
    val paging: Paging?
)