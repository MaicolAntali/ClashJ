package com.clashj.http.query

/**
 * Represents the base query parameters for pagination.
 *
 * @property limit The maximum number of items to be returned. Default is -1, which means no limit.
 * Limit the number of items returned in the response.
 *
 * @property before A cursor to paginate backward. Default is an empty string.
 * Return only items that occur before this marker. Before marker can be found from the response,
 * inside the 'paging' property. Note that only after or before can be specified for a request, not both.
 *
 * @property after A cursor to paginate forward. Default is an empty string.
 * Return only items that occur after this marker. Before marker can be found from the response,
 * inside the 'paging' property. Note that only after or before can be specified for a request, not both.
 */
data class PaginationQuery(
    val limit: Int = -1,
    val before: String = "",
    val after: String = ""
)
