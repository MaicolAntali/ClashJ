package com.clashj.model.component

/**
 * Represents a Clash of Clans API Badge.
 *
 * @param small The URL to the small-sized badge image.
 * @param medium The URL to the medium-sized badge image.
 * @param large The URL to the large-sized badge image.
 */
data class Badge(
    val small: String,
    val medium: String,
    val large: String,
)
