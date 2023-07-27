package com.clashj.model.component.clan

import com.clashj.model.component.Badge

data class WarClan(
    val destructionPercentage: Float,
    val tag: String,
    val name: String,
    val badgeUrls: Badge,
    val clanLevel: Int,
    val attacks: Int,
    val stars: Int,
    val expEarned: Int,
    val members: List<ClanWarMember>
)
