package com.clashj.model.league

import com.clashj.model.common.Paging

data class SimpleLeagueList(
    val items: List<SimpleLeague>,
    val paging: Paging?
)