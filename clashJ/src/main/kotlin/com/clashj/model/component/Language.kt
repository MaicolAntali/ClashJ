package com.clashj.model.component

/**
 * Represents a Clash of Clans API Language.
 *
 * @param name The name of the language.
 * @param id The ID of the language.
 * @param languageCode The language code of the language.
 */
data class Language(
    val name: String,
    val id: Int,
    val languageCode: String
)
