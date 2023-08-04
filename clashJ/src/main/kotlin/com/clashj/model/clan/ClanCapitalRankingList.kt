package com.clashj.model.clan

import com.clashj.model.common.Paging

data class ClanCapitalRankingList(
    val items: List<ClanCapitalRanking>,
    val paging: Paging?
)

data class ClanCapitalRanking(
    val clanPoints: Int,
    val clanBuilderBasePoints: Int,
)
