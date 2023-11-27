package io.github.maicolantali.types.api.model.clans.clanwar

import io.github.maicolantali.types.api.enums.WarResult

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
