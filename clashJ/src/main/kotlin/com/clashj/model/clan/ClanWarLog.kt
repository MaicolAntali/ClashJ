package com.clashj.model.clan

import com.clashj.model.common.Paging
import com.clashj.model.clan.component.WarClan
import com.clashj.model.clan.component.WarResult

/**
 * Represents the Clan War Log.
 *
 * @property items List of [ClanWarLogEntry].
 * @property paging Paging information for pagination. Can be null if there is no pagination.
 */
data class ClanWarLog(
    val items: List<ClanWarLogEntry>,
    val paging: Paging?
)

/**
 * Represents an entry in the Clan War Log.
 *
 * @property clan The clan information for the entry.
 * @property teamSize The size of the team in the war.
 * @property attacksPerMember The number of attacks allowed per member in the war.
 * @property opponent The information of the opponent clan.
 * @property endTime The end time of the war in string format.
 * @property result The result of the war for the clan.
 */
data class ClanWarLogEntry(
    val clan: WarClan,
    val teamSize: Int,
    val attacksPerMember: Int,
    val opponent: WarClan,
    val endTime: String,
    val result: WarResult,
)
