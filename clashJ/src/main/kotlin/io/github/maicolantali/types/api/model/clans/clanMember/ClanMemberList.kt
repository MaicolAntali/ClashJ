package io.github.maicolantali.types.api.model.clans.clanMember

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *ClanMemberList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanMember] objects representing the clan members in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanMemberList(
    override val items: List<ClanMember>,
    override val paging: Paging?,
) : ApiListResponse<ClanMember>
