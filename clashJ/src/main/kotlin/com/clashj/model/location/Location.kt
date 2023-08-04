package com.clashj.model.location

data class Location(
    val localizedName: String,
    val id: Int,
    val name: String,
    val isCountry: Boolean,
    val countryCode: String
)
