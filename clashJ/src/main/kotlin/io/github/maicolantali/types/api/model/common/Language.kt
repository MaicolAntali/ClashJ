package io.github.maicolantali.types.api.model.common

/**
 * Represents the *Language* model of the Clash of Clans API.
 *
 * @property name The name of the language.
 * @property id The unique identifier for the language.
 * @property languageCode The language code corresponding to the language.
 */
data class Language(
    val name: String,
    val id: Int,
    val languageCode: String,
)
