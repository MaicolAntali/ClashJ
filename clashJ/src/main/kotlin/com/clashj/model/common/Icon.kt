package com.clashj.model.common

/**
 * Represents the *Icon* model of the Clash of Clans API.
 *
 * @property tiny The URL of the tiny-sized image.
 * @property small The URL of the small-sized image.
 * @property medium The URL of the medium-sized image.
 */
data class Icon(
    val tiny: String,
    val small: String,
    val medium: String,
)
