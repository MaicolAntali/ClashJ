package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeasonAttack* model of the Clash of Clans API.
 *
 * @property attacker The information about the attacker participating in the attack.
 * @property destructionPercent The percentage of destruction achieved in the attack.
 * @property stars The number of stars earned in the attack.
 */
data class ClanCapitalRaidSeasonAttack(
    val attacker: ClanCapitalRaidSeasonAttacker,
    val destructionPercent: Int,
    val stars: Int,
)
