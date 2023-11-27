package io.github.maicolantali.types.api.model.clans.clanwar

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
    val duration: Int,
)
