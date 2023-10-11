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
    val attacks: List<ClanWarAttack>?
)

/**
 * Represents the *ClanWarAttack* model of the Clash of Clans API.
 * The ClanWarAttack data class stores information about an attack made during a clan war.
 *
 * @property order The order in which the attack occurred during the war.
 * @property attackerTag The tag of the player who launched the attack.
 * @property defenderTag The tag of the opponent's base that was attacked.
 * @property stars The number of stars earned in the attack.
 * @property destructionPercentage The percentage of destruction achieved in the attack.
 * @property duration The duration of the attack in seconds.
 */
data class ClanWarAttack(
    val order: Int,
    val attackerTag: String,
    val defenderTag: String,
    val stars: Int,
    val destructionPercentage: Int,
    val duration: Int
)

