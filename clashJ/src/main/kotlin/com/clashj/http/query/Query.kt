package com.clashj.http.query

/**
 * An interface representing a query to be used in API requests.
 */
interface Query {

    /**
     * Creates the query string based on the query parameters.
     *
     * @return The query string representing the query.
     */
    fun createQuery(): String
}