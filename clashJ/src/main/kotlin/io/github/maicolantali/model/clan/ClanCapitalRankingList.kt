package io.github.maicolantali.model.clan

import io.github.maicolantali.model.common.Paging

/**
 * Represents the *ClanCapitalRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanCapitalRanking] objects representing the clan capital rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanCapitalRankingList(
    val items: List<ClanCapitalRanking>,
    val paging: Paging?,
)

/**
 * Represents the *ClanCapitalRanking* model of the Clash of Clans API.
 * The ClanCapitalRanking data class stores information about a single clan capital's ranking.
 *
 * @property clanPoints The total points earned by the clan's capital.
 * @property clanBuilderBasePoints The builder base points earned by the clan's capital.
 */

data class ClanCapitalRanking(
    val clanPoints: Int,
    val clanBuilderBasePoints: Int,
)
