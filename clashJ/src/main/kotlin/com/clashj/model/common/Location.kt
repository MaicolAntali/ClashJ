package com.clashj.model.common

/**
 * Represents the *Location* model of the Clash of Clans API.
 *
 * @property id The unique identifier for the location.
 * @property name The name of the location.
 * @property isCountry A flag indicating whether the location is a country (`true`) or a region (`false`).
 * @property countryCode The country code corresponding to the location.
 * @property localizedName The localized name of the location. (*Note: the use of this is unknown at present.*)
 */
data class Location(
    val id: String,
    val name: String,
    val isCountry: Boolean,
    val countryCode: String,
    val localizedName: String,
)
