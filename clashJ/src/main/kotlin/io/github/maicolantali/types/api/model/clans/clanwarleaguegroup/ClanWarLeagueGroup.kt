package io.github.maicolantali.types.api.model.clans.clanwarleaguegroup

/**
 * Represents the *ClanWarLeagueGroup* model of the Clash of Clans API.
 *
 * @property tag The  tag of the clan war league group.
 * @property state The current state of the clan war league group.
 * @property season The season identifier of the clan war league group.
 * @property clans A list of [ClanWarLeagueClan] objects representing the clans in the group.
 * @property rounds A list of [ClanWarLeagueRound] objects representing the rounds of the clan war league group.
 */
data class ClanWarLeagueGroup(
    val tag: String,
    val state: ClanWarLeagueGroupState,
    val season: String,
    val clans: List<ClanWarLeagueClan>,
    val rounds: List<ClanWarLeagueRound>,
)
