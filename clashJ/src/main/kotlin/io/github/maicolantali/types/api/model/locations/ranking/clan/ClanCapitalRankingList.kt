package io.github.maicolantali.types.api.model.locations.ranking.clan

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanCapitalRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanCapitalRanking] objects representing the clan capital rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanCapitalRankingList(
    override val items: List<ClanCapitalRanking>,
    override val paging: Paging?,
) : ApiListResponse<ClanCapitalRanking>


