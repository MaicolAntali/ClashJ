package com.clashj.http.query

import java.net.URLEncoder

class SearchClanQuery(
    private val name: String = "",
    private val warFrequency: String = "",
    private val locationId: Int = -1,
    private val minMembers: Int = -1,
    private val maxMembers: Int = -1,
    private val minClanPoints: Int = -1,
    private val minClanLevel: Int = -1,
    private val paginationQuery: PaginationQuery = PaginationQuery(),
    private val labelIds: String = ""
) : Query {

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