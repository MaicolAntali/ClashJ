package com.clashj.model.component.clan

data class ClanWarMember(
    val tag: String,
    val name: String,
    val mapPosition: Int,
    val townhallLevel: Int,
    val opponentAttacks: Int,
    val bestOpponentAttack: ClanWarAttack,
    val attacks: List<ClanWarAttack>
)
