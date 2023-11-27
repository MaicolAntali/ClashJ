package io.github.maicolantali.types.api.model.clans.clanwarleaguegroup

/**
 * Represents the *ClanWarLeagueClanMember* model of the Clash of Clans API.
 * The ClanWarLeagueClanMember data class stores information about a clan member
 * participating in the clan war league group.
 *
 * @property tag The tag of the clan member.
 * @property townHallLevel The town hall level of the clan member.
 * @property name The name of the clan member.
 */
data class ClanWarLeagueClanMember(
    val tag: String,
    val townHallLevel: Int,
    val name: String,
)
