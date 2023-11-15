package io.github.maicolantali.http.query

import java.net.URLEncoder

/**
 * Represents a query used to search for clans.
 * This query allows filtering clans based on various criteria like name, war frequency,
 * location, number of members, clan points, clan level, pagination settings, and label IDs.
 *
 * @property name The name of the clan to search for (optional).
 * If provided, the search will be filtered based on this name.
 * @property warFrequency The war frequency of the clan to search for (optional).
 * If provided, the search will be filtered based on this war frequency.
 * @property locationId The location ID of the clan to search for (optional).
 * If provided, the search will be filtered based on this location ID.
 * @property minMembers The minimum number of members in the clan to search for (optional).
 * If provided, the search will be filtered based on this minimum member count.
 * @property maxMembers The maximum number of members in the clan to search for (optional).
 * If provided, the search will be filtered based on this maximum member count.
 * @property minClanPoints The minimum clan points of the clan to search for (optional).
 * If provided, the search will be filtered based on this minimum clan points value.
 * @property minClanLevel The minimum clan level of the clan to search for (optional).
 * If provided, the search will be filtered based on this minimum clan level value.
 * @property paginationQuery An instance of [PaginationQuery] used for pagination settings.
 * The pagination settings determine the number of results to retrieve and the starting point
 * for the search (optional).
 * @property labelIds A comma-separated string containing label IDs of the clan to search for (optional).
 * If provided, the search will be filtered based on these label IDs.
 *
 * @constructor Creates a [SearchClanQuery] instance with the provided query parameters.
 */
class SearchClanQuery(
    private val name: String = "",
    private val warFrequency: String = "",
    private val locationId: Int = -1,
    private val minMembers: Int = -1,
    private val maxMembers: Int = -1,
    private val minClanPoints: Int = -1,
    private val minClanLevel: Int = -1,
    private val paginationQuery: PaginationQuery = PaginationQuery(),
    private val labelIds: String = "",
) : Query {
    /**
     * Generates a formatted query string based on the provided query parameters.
     *
     * @return The formatted query string representing the search parameters.
     */
    override fun createQuery(): String {
        var query = ""

        if (this.name.isNotBlank()) {
            query += "name=${URLEncoder.encode(this.name, Charsets.UTF_8)}&"
        }

        if (this.warFrequency.isNotBlank()) {
            query += "warFrequency=${URLEncoder.encode(this.warFrequency, Charsets.UTF_8)}&"
        }

        if (this.locationId != -1) {
            query += "locationId=${this.locationId}&"
        }

        if (this.minMembers != -1) {
            query += "minMembers=${this.minMembers}&"
        }

        if (this.maxMembers != -1) {
            query += "maxMembers=${this.maxMembers}&"
        }

        if (this.minClanPoints != -1) {
            query += "minClanPoints=${this.minClanPoints}&"
        }

        if (this.minClanLevel != -1) {
            query += "minClanLevel=${this.minClanLevel}&"
        }

        query += paginationQuery.createQuery()

        if (this.labelIds.isNotBlank()) {
            query += "labelIds=${URLEncoder.encode(this.labelIds, Charsets.UTF_8)}&"
        }

        return query
    }
}
