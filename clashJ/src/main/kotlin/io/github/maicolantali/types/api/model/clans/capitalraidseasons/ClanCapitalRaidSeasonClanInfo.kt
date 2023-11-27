package io.github.maicolantali.types.api.model.clans.capitalraidseasons

import io.github.maicolantali.types.api.model.common.Badge

/**
 * Represents the *ClanCapitalRaidSeasonClanInfo* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasonClanInfo data class stores information about a clan
 * participating in the Clan Capital Raid season.
 *
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property level The level of the clan.
 * @property badgeUrls The badge URLs representing the clan's badge in different sizes.
 */
data class ClanCapitalRaidSeasonClanInfo(
    val tag: String,
    val name: String,
    val level: Int,
    val badgeUrls: Badge,
)
