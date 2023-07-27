package com.clashj.model.component

/**
 * Represents a Clash of Clans API Location.
 *
 * @param id The unique identifier of the location.
 * @param name The name of the location.
 * @param isCountry Specifies whether the location represents a country. `true` if it's a country, `false` otherwise.
 * @param countryCode The country code associated with the location.
 * @param localizedName The localized name of the location.
 */
data class Location(
    val id: String,
    val name: String,
    val isCountry: Boolean,
    val countryCode: String,
    val localizedName: String
)
