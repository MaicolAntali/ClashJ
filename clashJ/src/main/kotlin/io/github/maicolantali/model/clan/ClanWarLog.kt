package io.github.maicolantali.model.clan

import io.github.maicolantali.model.clan.component.WarClan
import io.github.maicolantali.model.clan.component.WarResult
import io.github.maicolantali.model.common.Paging

/**
 * Represents the *ClanWarLog* model of the Clash of Clans API.
 *
 * @property items A list of [ClanWarLogEntry] objects representing the clan war log entries in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanWarLog(
    val items: List<ClanWarLogEntry>,
    val paging: Paging?,
)

/**
 * Represents the *ClanWarLogEntry* model of the Clash of Clans API.
 * The ClanWarLogEntry data class stores information about a single clan war log entry.
 *
 * @property clan The information about the clan participating in the war.
 * @property teamSize The size of the team participating in the war.
 * @property attacksPerMember The number of attacks allowed per member in the war.
 * @property opponent The information about the opponent clan in the war.
 * @property endTime The end time of the war in ISO 8601 date-time format.
 * @property result The result of the clan war (win, lose, tie).
 */
data class ClanWarLogEntry(
    val clan: WarClan,
    val teamSize: Int,
    val attacksPerMember: Int,
    val opponent: WarClan,
    val endTime: String,
    val result: WarResult,
)
