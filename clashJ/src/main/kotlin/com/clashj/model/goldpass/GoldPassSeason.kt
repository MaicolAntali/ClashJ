package com.clashj.model.goldpass

/**
 * Represents the *GoldPassSeason* model of the Clash of Clans API.
 * The GoldPassSeason data class stores information about a Gold Pass season.
 *
 * @property startTime The start time of the Gold Pass season.
 * @property endTime The end time of the Gold Pass season.
 */
data class GoldPassSeason(
    val startTime: String,
    val endTime: String
)
