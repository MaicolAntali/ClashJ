package io.github.maicolantali.types.api.model.clans.clanwarleaguegroup

/**
 * Represents the *ClanWarLeagueRound* model of the Clash of Clans API.
 * The ClanWarLeagueRound data class stores information about a round in the clan war league group.
 *
 * @property warTags A list of unique tags representing the wars in the round.
 */
data class ClanWarLeagueRound(
    val warTags: List<String>,
)

