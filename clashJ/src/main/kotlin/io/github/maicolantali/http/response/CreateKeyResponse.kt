package io.github.maicolantali.http.response

import io.github.maicolantali.http.response.base.Key
import io.github.maicolantali.http.response.base.Status

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
    val key: Key,
)
