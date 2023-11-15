package io.github.maicolantali.model.location

import io.github.maicolantali.model.common.Location
import io.github.maicolantali.model.common.Paging

/**
 * Represents the *LocationList* model of the Clash of Clans API.
 *
 * @property items A list of [Location] objects representing individual locations.
 * @property paging Information about the paging of the location list.
 */
data class LocationList(
    val items: List<Location>,
    val paging: Paging?,
)
