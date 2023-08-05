package com.clashj.model.clan.component

import com.clashj.model.common.Badge

/**
 * Represents the *WarClan* model of the Clash of Clans API.
 *
 * @property destructionPercentage The overall destruction percentage achieved by the clan in the war.
 * @property tag The tag of the clan in the war.
 * @property name The name of the clan in the war.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
 * @property clanLevel The level of the clan.
 * @property attacks The total number of attacks.
 * @property stars The total number of stars earned.
 * @property expEarned The total amount of experience earned.
 * @property members A list of [ClanWarMember] objects containing information about individual member's participation.
 */
data class WarClan(
    val destructionPercentage: Float,
    val tag: String,
    val name: String,
    val badgeUrls: Badge,
    val clanLevel: Int,
    val attacks: Int,
    val stars: Int,
    val expEarned: Int,
    val members: List<ClanWarMember>?
)
