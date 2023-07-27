package com.clashj.model.component.clan

import com.clashj.model.component.league.League
import com.clashj.model.component.PlayerHouse
import com.clashj.model.component.league.SimpleLeague
import com.clashj.model.component.clan.ClanMemberRole

/**
 * Represents a member of a clan in the game.
 *
 * @property league The league of the clan member.
 * @property builderBaseLeague The league of the clan member in Builder Base.
 * @property playerHouse The player house elements of the clan member.
 * @property tag The unique tag of the clan member.
 * @property versusTrophies The number of versus trophies of the clan member.
 * @property name The name of the clan member.
 * @property role The role of the clan member in the clan.
 * @property expLevel The experience level of the clan member.
 * @property clanRank The rank of the clan member in the clan.
 * @property previousClanRank The previous rank of the clan member in the clan.
 * @property donations The number of troops donated by the clan member.
 * @property donationsReceived The number of troops received by the clan member.
 * @property trophies The number of trophies of the clan member.
 * @property builderBaseTrophies The number of Builder Base trophies of the clan member.
 */
data class ClanMember(
    val league: League,
    val builderBaseLeague: SimpleLeague,
    val playerHouse: PlayerHouse,
    val tag: String,
    val versusTrophies: Int,
    val name: String,
    val role: ClanMemberRole,
    val expLevel: Int,
    val clanRank: Int,
    val previousClanRank: Int,
    val donations: Int,
    val donationsReceived: Int,
    val trophies: Int,
    val builderBaseTrophies: Int
)
