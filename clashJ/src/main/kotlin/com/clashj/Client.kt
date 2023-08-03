package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.http.query.PaginationQuery
import com.clashj.http.query.SearchClanQuery
import com.clashj.model.clan.Clan
import com.clashj.model.clan.ClanCapitalRaidSeasons
import com.clashj.model.clan.ClanList
import com.clashj.model.clan.ClanMemberList
import com.clashj.model.clan.ClanWar
import com.clashj.model.clan.ClanWarLeagueGroup
import com.clashj.model.clan.ClanWarLog
import com.clashj.model.player.Player
import com.clashj.util.API_BASE_URL
import com.clashj.util.encodeTag

class Client(
    private val requestHandler: RequestHandler
) {

    suspend fun login() {
        requestHandler.login()
    }

    suspend fun searchClan(searchClanQuery: SearchClanQuery): ClanList {
        val query = searchClanQuery.createQuery()

        return requestHandler.request("$API_BASE_URL/clans?$query")
    }

    suspend fun getClanWarLeagueGroup(clanTag: String): ClanWarLeagueGroup {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar/leaguegroup")
    }

    suspend fun getClanWarLeagueWar(warTag: String): ClanWar {
        return requestHandler.request("$API_BASE_URL/clanwarleagues/wars/${encodeTag(warTag)}")
    }

    suspend fun getClanCurrentWar(clanTag: String): ClanWar {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar")
    }

    suspend fun getClan(clanTag: String): Clan {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}")
    }

    suspend fun getClanWarLog(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanWarLog {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/warlog?$query")
    }

    suspend fun getClanMembers(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanMemberList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/members?$query")
    }

    suspend fun getClanCapitalRaidSeasons(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanCapitalRaidSeasons {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/capitalraidseasons?$query")
    }

    suspend fun getPlayer(playerTag: String): Player {
        return requestHandler.request("$API_BASE_URL/players/${encodeTag(playerTag)}")
    }
}
