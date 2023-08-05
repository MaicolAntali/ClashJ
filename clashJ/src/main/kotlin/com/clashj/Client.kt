package com.clashj

import com.clashj.exception.InvalidCredentialException
import com.clashj.exception.MaintenanceException
import com.clashj.exception.BadGatewayException
import com.clashj.exception.NotFoundException
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
import com.clashj.model.label.LabelList
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

/**
 * Client class is used to interact with the Clash of Clans API.
 *
 * This class provides methods to interact with the Clash of Clans API
 * by making HTTP requests using the provided [requestHandler].
 * It encapsulates the logic for making specific API calls and handling the response data.
 *
 * @param requestHandler The [RequestHandler] instance responsible for handling HTTP requests and responses.
 */
class Client(
    private val requestHandler: RequestHandler
) {

    /**
     * Performs login into the Clash of Clans developer website using the provided credentials.
     *
     * The function uses the [requestHandler] to perform the login request and handle the response.
     * If the login is successful, temporary API keys are retrieved and filtered based on the current IP address.
     *
     * @throws InvalidCredentialException if the provided login credentials are incorrect or invalid.
     */
    suspend fun login() {
        requestHandler.login()
    }

    /**
     * Searches for clans based on the provided [searchClanQuery].
     *
     * @param searchClanQuery The [SearchClanQuery] object containing various search parameters.
     * @return A [ClanList] object containing a list of clans that match the search criteria.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun searchClan(searchClanQuery: SearchClanQuery): ClanList {
        val query = searchClanQuery.createQuery()

        return requestHandler.request("$API_BASE_URL/clans?$query")
    }

    /**
     * Gets the Clan War League group for the clan specified by [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the Clan War League group.
     * @return A [ClanWarLeagueGroup] object representing the Clan War League group for the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLeagueGroup(clanTag: String): ClanWarLeagueGroup {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar/leaguegroup")
    }

    /**
     * Gets the Clan War League war specified by [warTag].
     *
     * @param warTag The tag of the Clan War League war to retrieve.
     * @return A [ClanWar] object representing the Clan War League war with the specified tag.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested war is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLeagueWar(warTag: String): ClanWar {
        return requestHandler.request("$API_BASE_URL/clanwarleagues/wars/${encodeTag(warTag)}")
    }

    /**
     * Retrieves the current war of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the current war status.
     * @return A [ClanWar] object representing the current war status of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or does not have an ongoing war.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanCurrentWar(clanTag: String): ClanWar {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar")
    }

    /**
     * Retrieves detailed information about a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the detailed information.
     * @return A [Clan] object representing the detailed information of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClan(clanTag: String): Clan {
        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}")
    }

    /**
     * Retrieves the war log of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the war log.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanWarLog] object representing the war log of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no war log data available.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLog(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanWarLog {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/warlog?$query")
    }

    /**
     * Retrieves the list of members in a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the member list.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanMemberList] object representing the list of members in the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no members.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanMembers(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanMemberList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/members?$query")
    }

    /**
     * Retrieves the capital raid seasons of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the capital raid seasons.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanCapitalRaidSeasons] object representing the capital raid seasons of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no capital raid season data available.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanCapitalRaidSeasons(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanCapitalRaidSeasons {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/capitalraidseasons?$query")
    }

    /**
     * Retrieves detailed information about a player based on the provided [playerTag].
     *
     * @param playerTag The tag of the player for which to retrieve the information.
     * @return A [Player] object representing detailed information about the specified player.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested player is not found or has no available information.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getPlayer(playerTag: String): Player {
        return requestHandler.request("$API_BASE_URL/players/${encodeTag(playerTag)}")
    }

    /**
     * Retrieves a list of capital leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeagueList] object representing the list of capital leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCapitalLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/capitalleagues?$query")
    }

    /**
     * Retrieves detailed information about a specific capital league based on the provided [leagueId].
     *
     * @param leagueId The ID of the capital league for which to retrieve the information.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeague] object representing detailed information about the specified capital league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested capital league is not found or has no available information.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCapitalLeague(leagueId: String, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/capitalleagues/$leagueId?$query")
    }

    /**
     * Retrieves a list of leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [LeagueList] object representing the list of leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagues(pagination: PaginationQuery = PaginationQuery()): LeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues?$query")
    }

    /**
     * Retrieves detailed information about a specific league based on the provided [leagueId].
     *
     * @param leagueId The ID of the league for which to retrieve the information.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [League] object representing detailed information about the specified league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeague(leagueId: String, pagination: PaginationQuery = PaginationQuery()): League {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues/$leagueId?$query")
    }

    /**
     * Retrieves a list of seasons for a specific league based on the provided [leagueId].
     *
     * @param leagueId The ID of the league for which to retrieve the list of seasons.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [LeagueSeasonList] object representing the list of seasons for the specified league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagueSeasons(leagueId: String, pagination: PaginationQuery = PaginationQuery()): LeagueSeasonList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons?$query")
    }

    /**
     * Retrieves the ranking of players for a specific league season based on the provided [leagueId] and [seasonId].
     *
     * @param leagueId The ID of the league for which to retrieve the ranking.
     * @param seasonId The ID of the season for which to retrieve the ranking.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [PlayerRankingList] object representing the ranking of players for the specified league season.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league or season or its ranking is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagueSeasonRanking(
        leagueId: String,
        seasonId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons/$seasonId?$query")
    }

    /**
     * Retrieves the list of Builder Base leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeagueList] object representing the list of Builder Base leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getBuilderBaseLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/builderbaseleagues?$query")
    }

    /**
     * Retrieves information about a specific Builder Base league based on the provided [leagueId].
     *
     * @param leagueId The ID of the Builder Base league to retrieve.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeague] object representing the specified Builder Base league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getBuilderBaseLeague(leagueId: Int, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/builderbaseleagues/$leagueId?$query")
    }

    /**
     * Retrieves the list of War Leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeagueList] object representing the list of War Leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getWarLeagues(pagination: PaginationQuery = PaginationQuery()): SimpleLeagueList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/warleagues?$query")
    }

    /**
     * Retrieves information about a specific War League based on the provided [leagueId].
     *
     * @param leagueId The ID of the War League to retrieve.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [SimpleLeague] object representing the specified War League.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getWarLeague(leagueId: Int, pagination: PaginationQuery = PaginationQuery()): SimpleLeague {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/warleagues/$leagueId?$query")
    }

    /**
     * Retrieves the list of available locations.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [LocationList] object representing the list of available locations.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocations(pagination: PaginationQuery = PaginationQuery()): LocationList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations?$query")
    }

    /**
     * Retrieves information about a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location to retrieve.
     * @return A [Location] object representing the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocation(locationId: String): Location {
        return requestHandler.request("$API_BASE_URL/locations/$locationId")
    }

    /**
     * Retrieves the list of clans ranked in a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanRankingList] object representing the list of clans ranked in the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationClanRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans?$query")
    }

    /**
     * Retrieves the list of players ranked in a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the player rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [PlayerRankingList] object representing the list of players ranked in the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationPlayerRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players?$query")
    }

    /**
     * Retrieves the list of clans
     * ranked in the Builder Base for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Builder Base clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanBuilderBaseRankingList] object representing the list of clans ranked in the Builder Base
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationClanBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanBuilderBaseRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans-builder-base?$query")
    }

    /**
     * Retrieves the list of players
     * ranked in the Builder Base for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Builder Base player rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [PlayerBuilderBaseRankingList] object representing the list of players ranked in the Builder Base
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */

    suspend fun getLocationPlayerBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): PlayerBuilderBaseRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players-builder-base?$query")
    }

    /**
     * Retrieves the list of clans ranked in the Capital for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Capital clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [ClanCapitalRankingList] object representing the list of clans ranked in the Capital
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationCapitalRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery()
    ): ClanCapitalRankingList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/capitals?$query")
    }

    /**
     * Retrieves the current Gold Pass season information.
     *
     * @return A [GoldPassSeason] object representing the current Gold Pass season information.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCurrentGoldPass(): GoldPassSeason {
        return requestHandler.request("$API_BASE_URL/goldpass/seasons/current")
    }

    /**
     * Retrieves a list of player labels.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     *
     * @return A [LabelList] containing the list of player labels.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getPlayerLabels(pagination: PaginationQuery = PaginationQuery()): LabelList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/labels/players?$query")
    }

    /**
     * Retrieves a list of clan labels.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     *
     * @return A [LabelList] containing the list of clan labels.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanLabels(pagination: PaginationQuery = PaginationQuery()): LabelList {
        val query = pagination.createQuery()

        return requestHandler.request("$API_BASE_URL/labels/clans?$query")
    }
}
