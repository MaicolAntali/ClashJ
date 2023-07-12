package com.clashj.http.response

import com.clashj.http.response.base.Key
import com.clashj.http.response.base.Status

/**
 * Represents the response for retrieving the list of the existing keys.
 *
 * Encapsulates the response information for retrieving a list of keys,
 * including the [status] of the operation and the list of [keys] objects.
 *
 * @property status The status information of response.
 * @property keys The list of the existing keys.
 */
data class KeyListResponse(
    val status: Status,
    val keys: List<Key>
)
