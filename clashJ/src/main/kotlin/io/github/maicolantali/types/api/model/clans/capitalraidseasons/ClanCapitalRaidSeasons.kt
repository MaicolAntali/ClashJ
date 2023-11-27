package io.github.maicolantali.types.api.model.clans.capitalraidseasons

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanCapitalRaidSeasons* model of the Clash of Clans API.
 * The ClanCapitalRaidSeasons data class stores information about Clan Capital Raid seasons.
 *
 * @property items A list of [ClanCapitalRaidSeason] objects representing individual Clan Capital Raid seasons.
 * @property paging Information about the paging of the Clan Capital Raid seasons.
 */
data class ClanCapitalRaidSeasons(
    override val items: List<ClanCapitalRaidSeason>,
    override val paging: Paging?,
) : ApiListResponse<ClanCapitalRaidSeason>
