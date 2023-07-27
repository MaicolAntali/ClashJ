package com.clashj.model

import com.clashj.model.component.clan.WarClan
import com.clashj.model.component.clan.WarState

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
