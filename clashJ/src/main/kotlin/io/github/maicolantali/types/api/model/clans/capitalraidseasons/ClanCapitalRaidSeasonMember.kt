package io.github.maicolantali.types.api.model.clans.capitalraidseasons


/**
 * Represents the *ClanCapitalRaidSeasonMember* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonMember data class stores information about a member
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the member.
 * @property name The name of the member.
 * @property attacks The number of attacks made by the member during the season.
 * @property attackLimit The maximum number of regular attacks allowed for the member during the season.
 * @property bonusAttackLimit The maximum number of bonus attacks allowed for the member during the season.
 * @property capitalResourcesLooted The total amount of capital resources looted by the member during the season.
 */
data class ClanCapitalRaidSeasonMember(
    val tag: String,
    val name: String,
    val attacks: Int,
    val attackLimit: Int,
    val bonusAttackLimit: Int,
    val capitalResourcesLooted: Int,
)
