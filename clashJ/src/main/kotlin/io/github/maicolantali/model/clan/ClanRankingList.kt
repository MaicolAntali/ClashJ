package io.github.maicolantali.model.clan

import io.github.maicolantali.model.common.Location
import io.github.maicolantali.model.common.Paging

/**
 * Represents the *ClanRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanRanking] objects representing the clan rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanRankingList(
    val items: List<ClanRanking>,
    val paging: Paging?,
)

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
