package io.github.maicolantali.model.league

/**
 * Represents the *SimpleLeague* model of the Clash of Clans API.
 *
 * @property name The name of the league.
 * @property id The unique identifier for the league.
 */
data class SimpleLeague(
    val name: String,
    val id: Int,
)
