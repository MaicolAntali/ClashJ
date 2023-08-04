package com.clashj.model.player

import com.clashj.model.common.Paging
import com.clashj.model.league.SimpleLeague

data class PlayerBuilderBaseRankingList(
    val items: List<PlayerBuilderBaseRanking>,
    val paging: Paging?
)

data class PlayerBuilderBaseRanking(
    val clan: PlayerRankingClan,
    val builderBaseLeague: SimpleLeague,
    val versusTrophies: Int,
    val versusBattleWins: Int,
    val tag: String,
    val name: String,
    val expLevel: Int,
    val rank: Int,
    val previousRank: Int,
    val builderBaseTrophies: Int,
)
