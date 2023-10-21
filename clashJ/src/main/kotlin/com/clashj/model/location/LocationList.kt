package com.clashj.model.location

import com.clashj.model.common.Location
import com.clashj.model.common.Paging

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
