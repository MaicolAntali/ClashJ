package io.github.maicolantali.types.api.model.locations.ranking.player

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *PlayerRankingList* model of the Clash of Clans API.
 * The PlayerRankingList data class stores a list of player rankings.
 *
 * @property items A list of player rankings.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class PlayerRankingList(
    override val items: List<PlayerRanking>,
    override val paging: Paging?,
) : ApiListResponse<PlayerRanking>
