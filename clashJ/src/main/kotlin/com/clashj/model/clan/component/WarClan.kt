package com.clashj.model.clan.component

import com.clashj.model.common.Badge

/**
 * Represents a clan in a clan war.
 *
 * @property destructionPercentage The percentage of destruction achieved by the clan in the war.
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property badgeUrls The badge URLs of the clan.
 * @property clanLevel The level of the clan.
 * @property attacks The total number of attacks made by the clan in the war.
 * @property stars The total number of stars earned by the clan in the war.
 * @property expEarned The total number of experience points earned by the clan in the war.
 * @property members The list of clan members in the war. (It can be null)
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
