package io.github.maicolantali.types.api.model.locations.ranking.clan

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanRanking] objects representing the clan rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanRankingList(
    override val items: List<ClanRanking>,
    override val paging: Paging?,
) : ApiListResponse<ClanRanking>
