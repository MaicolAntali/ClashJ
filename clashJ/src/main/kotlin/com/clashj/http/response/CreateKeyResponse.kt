package com.clashj.http.response

import com.clashj.http.response.base.Key
import com.clashj.http.response.base.Status

/**
 * Represents the response for creating a new key on the developer site.
 *
 * Encapsulates the response information for creating a new key, including the
 * [status] of the operation and the created [key] object.
 *
 * @property status The status information of the operation.
 * @property key The created key.
 */
data class CreateKeyResponse(
    val status: Status,
    val key: Key
)
