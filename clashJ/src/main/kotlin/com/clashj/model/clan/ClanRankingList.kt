package com.clashj.model.clan

import com.clashj.model.common.Location
import com.clashj.model.common.Paging

data class ClanRankingList(
    val items: List<ClanRanking>,
    val paging: Paging?
)

data class ClanRanking(
    val clanLevel: Int,
    val clanPoints: Int,
    val location: Location,
    val members: Int,
    val tag: String,
    val name: String,
    val rank: Int,
    val previousRank: Int
)
