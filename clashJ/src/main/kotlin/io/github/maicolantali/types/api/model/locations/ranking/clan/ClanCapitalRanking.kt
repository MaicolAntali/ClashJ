package io.github.maicolantali.types.api.model.locations.ranking.clan

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
