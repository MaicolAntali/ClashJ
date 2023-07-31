package com.clashj.model.common

/**
 * Represents pagination information.
 *
 * @property cursors The cursors for navigating through the data.
 */
data class Paging(
    val cursors: Cursors
)

/**
 * Represents cursors for pagination.
 *
 * @property before The cursor for returning items that occur before this marker.
 * Can be null if there is no before marker.
 *
 * @property after The cursor for returning items that occur after this marker.
 * Can be null if there is no after marker.
 */
data class Cursors(
    val before: String?,
    val after: String?
)