package com.clashj.model.clan

import com.clashj.model.common.Paging
import com.clashj.model.clan.component.ClanMember

/**
 * Represents the *ClanMemberList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanMember] objects representing the clan members in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanMemberList(
    val items: List<ClanMember>,
    val paging: Paging?
)

