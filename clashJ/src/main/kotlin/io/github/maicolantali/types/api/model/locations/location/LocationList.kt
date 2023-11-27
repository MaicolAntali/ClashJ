package io.github.maicolantali.types.api.model.locations.location

import io.github.maicolantali.types.api.interfaces.ApiListResponse
import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents the *LocationList* model of the Clash of Clans API.
 *
 * @property items A list of [Location] objects representing individual locations.
 * @property paging Information about the paging of the location list.
 */
data class LocationList(
    override val items: List<Location>,
    override val paging: Paging?,
) : ApiListResponse<Location>
