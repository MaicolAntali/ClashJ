package com.clashj.model.clan

import com.clashj.model.common.Paging
import com.clashj.model.clan.component.ClanMember

/**
 * Represents the Clan War Log.
 *
 * @property items List of [ClanMember].
 * @property paging Paging information for pagination. Can be null if there is no pagination.
 */
data class ClanMemberList(
    val items: List<ClanMember>,
    val paging: Paging?
)

