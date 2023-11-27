package io.github.maicolantali.types.api.model.locations.ranking.clan

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanBuilderBaseRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanBuilderBaseRanking] objects representing the clan builder base rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanBuilderBaseRankingList(
    override val items: List<ClanBuilderBaseRanking>,
    override val paging: Paging?,
) : ApiListResponse<ClanBuilderBaseRanking>
