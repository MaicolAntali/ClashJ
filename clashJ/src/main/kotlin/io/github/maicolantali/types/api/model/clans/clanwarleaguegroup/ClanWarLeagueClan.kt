package io.github.maicolantali.types.api.model.clans.clanwarleaguegroup

import io.github.maicolantali.types.api.model.common.Badge

/**
 * Represents the *ClanWarLeagueClan* model of the Clash of Clans API.
 * The ClanWarLeagueClan data class stores information about a clan participating in the clan war league group.
 *
 * @property tag The tag of the clan.
 * @property clanLevel The level of the clan.
 * @property name The name of the clan.
 * @property members A list of [ClanWarLeagueClanMember] objects representing the members of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
 */
data class ClanWarLeagueClan(
    val tag: String,
    val clanLevel: Int,
    val name: String,
    val members: List<ClanWarLeagueClanMember>,
    val badgeUrls: Badge,
)
