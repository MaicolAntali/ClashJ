package io.github.maicolantali.types.api.interfaces

import io.github.maicolantali.types.api.model.common.paging.Paging

/**
 * Generic interface for API responses containing a list of items of type [T]
 * along with optional paging information.
 *
 * This interface is designed to encapsulate the structure of API responses that include
 * a list of items and information about paging.
 *
 * @param T The type of items contained in the response list.
 *
 * @property items The list of items of type [T] included in the API response.
 * @property paging Optional paging information. See [Paging].
 */
interface ApiListResponse<T> {
    val items: List<T>
    val paging: Paging?
}
