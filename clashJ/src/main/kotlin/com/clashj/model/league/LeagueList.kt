package com.clashj.model.league

import com.clashj.model.common.Paging

data class LeagueList(
    val items: List<League>,
    val paging: Paging?
)