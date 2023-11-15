package io.github.maicolantali.model.clan

import io.github.maicolantali.model.common.Paging

/**
 * Represents the *ClanBuilderBaseRankingList* model of the Clash of Clans API.
 *
 * @property items A list of [ClanBuilderBaseRanking] objects representing the clan builder base rankings in the list.
 * @property paging The pagination information for navigating through the list (optional).
 */
data class ClanBuilderBaseRankingList(
    val items: List<ClanBuilderBaseRanking>,
    val paging: Paging?,
)

/**
 * Represents the *ClanBuilderBaseRanking* model of the Clash of Clans API.
 * The ClanBuilderBaseRanking data class stores information about a single clan's builder base ranking.
 *
 * @property clanPoints The total points earned by the clan.
 * @property clanBuilderBasePoints The builder base points earned by the clan.
 * @property clanVersusPoints The versus points earned by the clan.
 */
data class ClanBuilderBaseRanking(
    val clanPoints: Int,
    val clanBuilderBasePoints: Int,
    val clanVersusPoints: Int,
)
