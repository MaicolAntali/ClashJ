package io.github.maicolantali.types.api.interfaces

import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Represents a generic interface for API responses containing a list of items of type [T]
 * along with optional paging information.
 *
 * @param T The type of items contained in the response list.
 */
interface ApiListResponse<T> {
    val items: List<T>
    val paging: Paging?
}
