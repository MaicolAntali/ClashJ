package io.github.maicolantali.types.api.model.leagues.leagueseason

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *LeagueSeasonList* model of the Clash of Clans API.
 *
 * @property items A list of [LeagueSeason] objects representing individual league seasons.
 * @property paging Information about the paging of the league season list.
 */
data class LeagueSeasonList(
    override val items: List<LeagueSeason>,
    override val paging: Paging?,
) : ApiListResponse<LeagueSeason>
