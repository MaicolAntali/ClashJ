package com.clashj.model.clan

import com.clashj.model.common.Paging

data class ClanList(
    val items: List<Clan>,
    val paging: Paging?
)
