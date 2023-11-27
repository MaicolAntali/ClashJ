package io.github.maicolantali.types.api.model.common

/**
 * Represents the *Badge* model of the Clash of Clans API.
 *
 * @property small The URL of the small-sized image.
 * @property medium The URL of the medium-sized image.
 * @property large The URL of the large-sized image.
 */
data class Badge(
    val small: String,
    val medium: String,
    val large: String,
)
