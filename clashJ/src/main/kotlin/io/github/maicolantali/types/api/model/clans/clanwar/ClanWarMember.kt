package io.github.maicolantali.types.api.model.clans.clanwar

/**
 * Represents the *ClanWarMember* model of the Clash of Clans API.
 * The ClanWarMember data class stores information about a member's participation in a clan war.
 *
 * @property tag The tag of the clan war member.
 * @property name The name of the clan war member.
 * @property mapPosition The map position of the clan war member's base in the war.
 * @property townhallLevel The town hall level of the clan war member's base.
 * @property opponentAttacks The number of attacks made by the opponent on the member's base.
 * @property bestOpponentAttack The best attack made by an opponent on the member's base.
 * @property attacks A list of attacks made by the clan war member during the war.
 */
data class ClanWarMember(
    val tag: String,
    val name: String,
    val mapPosition: Int,
    val townhallLevel: Int,
    val opponentAttacks: Int,
    val bestOpponentAttack: ClanWarAttack,
    val attacks: List<ClanWarAttack>?,
)
