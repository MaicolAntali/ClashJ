package com.clashj.model.common

/**
 * Represents a Clash of Clans API Icon.
 *
 * @param tiny The URL to the tiny-sized badge image.
 * @param small The URL to the small-sized badge image.
 * @param medium The URL to the medium-sized badge image.
 */
data class Icon(
    val tiny: String,
    val small: String,
    val medium: String,
)
