package io.github.maicolantali.types.api.model.locations.ranking.player

import io.github.maicolantali.types.api.model.common.paging.Paging
import io.github.maicolantali.types.api.interfaces.ApiListResponse

/**
 * Represents the *PlayerBuilderBaseRankingList* model of the Clash of Clans API.
 * The PlayerBuilderBaseRankingList data class stores a list of player rankings based on their builder base performance.
 *
 * @property items A list of player rankings based on their builder base performance.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class PlayerBuilderBaseRankingList(
    override val items: List<PlayerBuilderBaseRanking>,
    override val paging: Paging?,
) : ApiListResponse<PlayerBuilderBaseRanking>


