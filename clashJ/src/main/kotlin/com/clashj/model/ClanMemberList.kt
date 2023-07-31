package com.clashj.model

import com.clashj.model.component.Paging
import com.clashj.model.component.clan.ClanMember

/**
 * Represents the Clan War Log.
 *
 * @property items List of [ClanWarLogEntry].
 * @property paging Paging information for pagination. Can be null if there is no pagination.
 */
data class ClanMemberList(
    val items: List<ClanMember>,
    val paging: Paging?
)

