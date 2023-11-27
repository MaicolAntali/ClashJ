package io.github.maicolantali.types.api.model.clans.clan

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanList* model of the Clash of Clans API.
 *
 * @property items A list of [Clan] objects representing the clans in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanList(
    override val items: List<Clan>,
    override val paging: Paging?,
) : ApiListResponse<Clan>
