package com.clashj.model.component.clan


/**
 * Represents an attack made in a ClanWar.
 *
 * @property order The order of the attack in the war.
 * @property attackerTag The tag of the attacker.
 * @property defenderTag The tag of the defender.
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
