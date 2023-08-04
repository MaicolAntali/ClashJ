package com.clashj.model.clan

import com.clashj.model.common.Paging

data class ClanBuilderBaseRankingList(
    val items: List<ClanBuilderBaseRanking>,
    val paging: Paging?
)

data class ClanBuilderBaseRanking(
    val clanPoints: Int,
    val clanBuilderBasePoints: Int,
    val clanVersusPoints: Int
)
