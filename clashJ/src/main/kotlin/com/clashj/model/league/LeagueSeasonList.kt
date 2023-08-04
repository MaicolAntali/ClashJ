package com.clashj.model.league

import com.clashj.model.common.Paging

data class LeagueSeasonList(
    val items: List<LeagueSeason>,
    val paging: Paging?
)

data class LeagueSeason(
    val id: String
)