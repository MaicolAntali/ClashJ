package io.github.maicolantali.types.api.model.leagues.league

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *LeagueList* model of the Clash of Clans API.
 *
 * @property items A list of [League] objects representing individual leagues.
 * @property paging Information about the paging of the league list.
 */
data class LeagueList(
    override val items: List<League>,
    override val paging: Paging?,
) : ApiListResponse<League>
