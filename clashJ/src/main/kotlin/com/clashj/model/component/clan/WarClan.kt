package com.clashj.model.component.clan

import com.clashj.model.component.Badge

/**
 * Represents a Clan in a ClanWar.
 *
 * @property destructionPercentage The percentage of destruction achieved by the clan in the war.
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property badgeUrls The badge URLs of the clan.
 * @property clanLevel The level of the clan.
 * @property attacks The number of attacks made by the clan in the war.
 * @property stars The total number of stars earned by the clan in the war.
 * @property expEarned The experience points earned by the clan in the war.
 * @property members The list of members participating in the war from this clan.
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
    val members: List<ClanWarMember>
)
