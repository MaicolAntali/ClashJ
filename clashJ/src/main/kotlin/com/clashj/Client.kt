package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.model.Clan
import com.clashj.util.API_BASE_URL
import com.clashj.util.encodeTag

class Client(
    private val requestHandler: RequestHandler
) {

    suspend fun login() {
        requestHandler.login()
    }

    suspend fun getClan(clanTag: String): Clan {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}")
    }
}