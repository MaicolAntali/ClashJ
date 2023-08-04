package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.http.query.PaginationQuery
import com.clashj.http.query.SearchClanQuery
import com.clashj.model.clan.Clan
import com.clashj.model.clan.ClanBuilderBaseRankingList
import com.clashj.model.clan.ClanCapitalRaidSeasons
import com.clashj.model.clan.ClanCapitalRankingList
import com.clashj.model.clan.ClanList
import com.clashj.model.clan.ClanMemberList
import com.clashj.model.clan.ClanRankingList
import com.clashj.model.clan.ClanWar
import com.clashj.model.clan.ClanWarLeagueGroup
import com.clashj.model.clan.ClanWarLog
import com.clashj.model.goldpass.GoldPassSeason
import com.clashj.model.league.SimpleLeagueList
import com.clashj.model.league.League
import com.clashj.model.league.LeagueList
import com.clashj.model.league.LeagueSeasonList
import com.clashj.model.league.SimpleLeague
import com.clashj.model.location.Location
import com.clashj.model.location.LocationList
import com.clashj.model.player.Player
import com.clashj.model.player.PlayerBuilderBaseRankingList
import com.clashj.model.player.PlayerRankingList
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

    suspend fun getCapitalLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/capitalleagues?$query")
    }

    suspend fun getCapitalLeague(leagueId: String, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/capitalleagues/$leagueId?$query")
    }

    suspend fun getLeagues(pagination: PaginationQuery = PaginationQuery()): LeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues?$query")
    }

    suspend fun getLeague(leagueId: String, pagination: PaginationQuery = PaginationQuery()): League {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues/$leagueId?$query")
    }

    suspend fun getLeagueSeasons(leagueId: String, pagination: PaginationQuery = PaginationQuery()): LeagueSeasonList {
        val query = pagination.createQuery()

        println("$API_BASE_URL/leagues/$leagueId/seasons?$query")
        return requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons?$query")
    }

    suspend fun getLeagueSeasonRanking(
        leagueId: String,
        seasonId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons/$seasonId?$query")
    }

    suspend fun getBuilderBaseLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/builderbaseleagues?$query")
    }

    suspend fun getBuilderBaseLeague(leagueId: Int, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/builderbaseleagues/$leagueId?$query")
    }

    suspend fun getWarLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/warleagues?$query")
    }

    suspend fun getWarLeague(leagueId: Int, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/warleagues/$leagueId?$query")
    }

    suspend fun getLocations(pagination: PaginationQuery = PaginationQuery()): LocationList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations?$query")
    }

    suspend fun getLocation(locationId: String, pagination: PaginationQuery = PaginationQuery()): Location {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId?$query")
    }

    suspend fun getLocationClanRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans?$query")
    }

    suspend fun getLocationPlayerRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players?$query")
    }

    suspend fun getLocationClanBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanBuilderBaseRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans-builder-base?$query")
    }

    suspend fun getLocationPlayerBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerBuilderBaseRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players-builder-base?$query")
    }

    suspend fun getLocationCapitalRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanCapitalRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/capitals?$query")
    }

    suspend fun getCurrentGoldPass(): GoldPassSeason {
        return requestHandler.request("$API_BASE_URL/goldpass/seasons/current")
    }
}
