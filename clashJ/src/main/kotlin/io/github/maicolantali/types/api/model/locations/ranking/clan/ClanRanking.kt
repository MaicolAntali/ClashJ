package io.github.maicolantali.types.api.model.locations.ranking.clan

import io.github.maicolantali.types.api.model.locations.location.Location

/**
 * Represents the *ClanRanking* model of the Clash of Clans API.
 * The ClanRanking data class stores information about a single clan's ranking.
 *
 * @property clanLevel The level of the clan.
 * @property clanPoints The total points earned by the clan.
 * @property location The location of the clan.
 * @property members The total number of members in the clan.
 * @property tag The tag of the clan.
 * @property name The name of the clan.
 * @property rank The current ranking of the clan.
 * @property previousRank The previous ranking of the clan.
 */
data class ClanRanking(
    val clanLevel: Int,
    val clanPoints: Int,
    val location: Location,
    val members: Int,
    val tag: String,
    val name: String,
    val rank: Int,
    val previousRank: Int,
)
