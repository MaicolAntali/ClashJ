package com.clashj.model.clan.component

/**
 * Represents a member of a Clan participating in a ClanWar.
 *
 * @property tag The tag of the member.
 * @property name The name of the member.
 * @property mapPosition The position of the member on the Clan War map.
 * @property townhallLevel The townhall level of the member.
 * @property opponentAttacks The number of attacks made on this member by opponents in the war.
 * @property bestOpponentAttack The best attack made on this member by an opponent in the war.
 * @property attacks The list of attacks made by this member in the war.
 */
data class ClanWarMember(
    val tag: String,
    val name: String,
    val mapPosition: Int,
    val townhallLevel: Int,
    val opponentAttacks: Int,
    val bestOpponentAttack: ClanWarAttack,
    val attacks: List<ClanWarAttack>
)
