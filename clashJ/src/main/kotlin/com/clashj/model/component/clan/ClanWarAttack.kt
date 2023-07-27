package com.clashj.model.component.clan

data class ClanWarAttack(
    val order: Int,
    val attackerTag: String,
    val defenderTag: String,
    val stars: Int,
    val destructionPercentage: Int,
    val duration: Int
)
