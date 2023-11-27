package io.github.maicolantali.types.api.model.locations.ranking.clan

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
