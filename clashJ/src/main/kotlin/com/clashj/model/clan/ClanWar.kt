package com.clashj.model.clan

import com.clashj.model.clan.component.WarClan
import com.clashj.model.clan.component.WarState

/**
 * Represents the *ClanWar* model of the Clash of Clans API.
 *
 * @property clan The information about the clan participating in the war.
 * @property teamSize The size of the team participating in the war.
 * @property attacksPerMember The number of attacks allowed per member in the war.
 * @property opponent The information about the opponent clan in the war.
 * @property startTime The start time of the war in ISO 8601 date-time format.
 * @property state The current state of the clan war.
 * @property endTime The end time of the war in ISO 8601 date-time format.
 * @property preparationStartTime The start time of the preparation phase in ISO 8601 date-time format.
 */
data class ClanWar(
    val clan: WarClan,
    val teamSize: Int,
    val attacksPerMember: Int,
    val opponent: WarClan,
    val startTime: String,
    val state: WarState,
    val endTime: String,
    val preparationStartTime: String
)
