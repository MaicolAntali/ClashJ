package io.github.maicolantali.types.api.model.clans.capitalraidseasons

/**
 * Represents the *ClanCapitalRaidSeasonAttacker* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonAttacker data class stores information about an attacker
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the attacker.
 * @property name The name of the attacker.
 */
data class ClanCapitalRaidSeasonAttacker(
    val tag: String,
    val name: String,
)

