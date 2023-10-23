package com.clashj.model.clan

import com.clashj.model.common.Paging

/**
 * Represents the *ClanList* model of the Clash of Clans API.
 *
 * @property items A list of [Clan] objects representing the clans in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanList(
    val items: List<Clan>,
    val paging: Paging?,
)
