package com.clashj.model.player

import com.clashj.model.common.Badge
import com.clashj.model.common.Paging

data class PlayerRankingList(
    val items: List<PlayerRanking>,
    val paging: Paging?
)

data class PlayerRanking(
    val clan: PlayerRankingClan,
    val attackWins: Int,
    val defenseWins: Int,
    val tag: String,
    val name: String,
    val expLevel: Int,
    val rank: Int,
    val previousRank: Int,
    val trophies: Int
)

data class PlayerRankingClan(
    val tag: String,
    val name: String,
    val badgeUrls: Badge
)