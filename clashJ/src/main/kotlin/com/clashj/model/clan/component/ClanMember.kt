package com.clashj.model.clan.component

import com.clashj.model.common.PlayerHouse
import com.clashj.model.league.League
import com.clashj.model.league.SimpleLeague

/**
 * Represents the *ClanMember* model of the Clash of Clans API.
 *
 * @property league The league information of the clan member in the main village.
 * @property builderBaseLeague The league information of the clan member in the builder base village.
 * @property playerHouse The player house information of the clan member.
 * @property tag The tag of the clan member.
 * @property versusTrophies The number of versus trophies earned by the clan member.
 * @property name The name of the clan member.
 * @property role The role of the clan member within the clan.
 * @property expLevel The experience level of the clan member.
 * @property clanRank The rank of the clan member within the clan.
 * @property previousClanRank The previous rank of the clan member within the clan.
 * @property donations The number of troops donated by the clan member.
 * @property donationsReceived The number of troops received by the clan member from other clan members.
 * @property trophies The number of trophies earned by the clan member in the main village.
 * @property builderBaseTrophies The number of trophies earned by the clan member in the builder base village.
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
    val builderBaseTrophies: Int,
)
