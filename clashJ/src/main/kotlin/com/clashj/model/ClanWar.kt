package com.clashj.model

import com.clashj.model.component.clan.WarClan
import com.clashj.model.component.clan.WarState

/**
 * Represents a Clan War in the game.
 *
 * @property clan The clan participating in the war.
 * @property teamSize The size of the team in the war.
 * @property attacksPerMember The number of attacks allowed per member in the war.
 * @property opponent The opponent clan in the war.
 * @property startTime The start time of the war in ISO 8601 date-time format.
 * @property state The state of the war.
 * @property endTime The end time of the war in ISO 8601 date-time format.
 * @property preparationStartTime The start time of the war's preparation day in ISO 8601 date-time format.
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
