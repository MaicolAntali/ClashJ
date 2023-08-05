package com.clashj.model.location

/**
 * Represents the *Location* model of the Clash of Clans API.
 * The Location data class stores information about a location.
 *
 * @property localizedName The localized name of the location.
 * @property id The unique identifier of the location.
 * @property name The name of the location.
 * @property isCountry A flag indicating whether the location is a country or not.
 * @property countryCode The country code of the location.
 */
data class Location(
    val localizedName: String,
    val id: Int,
    val name: String,
    val isCountry: Boolean,
    val countryCode: String
)
