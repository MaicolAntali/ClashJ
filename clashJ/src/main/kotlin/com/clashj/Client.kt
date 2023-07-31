package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.http.query.PaginationQuery
import com.clashj.model.Clan
import com.clashj.model.ClanWar
import com.clashj.model.ClanWarLog
import com.clashj.util.API_BASE_URL
import com.clashj.util.encodeTag

class Client(
    private val requestHandler: RequestHandler
) {

    suspend fun login() {
        requestHandler.login()
    }

    suspend fun getClanWarLog(clanTag: String, pagination: PaginationQuery = PaginationQuery()): ClanWarLog {
        var query = "?"
        // Set the limit query
        if (pagination.limit != -1) {
            query += "limit=${pagination.limit}&"
        }

        // Set the before query
        if (pagination.before.isNotBlank()) {
            query += "before=${pagination.before}&"
        }

        // Set the after query
        if (pagination.before.isBlank() && pagination.after.isNotBlank()) {
            query += "after=${pagination.after}&"
        }

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/warlog" + query)
    }

    suspend fun getClanCurrentWar(clanTag: String): ClanWar {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar")
    }

    suspend fun getClan(clanTag: String): Clan {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}")
    }

}
