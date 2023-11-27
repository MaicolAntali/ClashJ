package io.github.maicolantali.types.api.model.clans.clanwar

import io.github.maicolantali.types.api.model.common.paging.Paging
import io.github.maicolantali.types.api.interfaces.ApiListResponse

/**
 * Represents the *ClanWarLog* model of the Clash of Clans API.
 *
 * @property items A list of [ClanWarLogEntry] objects representing the clan war log entries in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanWarLog(
    override val items: List<ClanWarLogEntry>,
    override val paging: Paging?,
) : ApiListResponse<ClanWarLogEntry>
