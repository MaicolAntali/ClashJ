package io.github.maicolantali

import io.github.maicolantali.exception.BadGatewayException
import io.github.maicolantali.exception.InvalidCredentialException
import io.github.maicolantali.exception.MaintenanceException
import io.github.maicolantali.exception.NotFoundException
import io.github.maicolantali.http.query.PaginationQuery
import io.github.maicolantali.http.query.SearchClanQuery
import io.github.maicolantali.types.api.model.clans.capitalraidseasons.ClanCapitalRaidSeasons
import io.github.maicolantali.types.api.model.clans.clan.Clan
import io.github.maicolantali.types.api.model.clans.clan.ClanList
import io.github.maicolantali.types.api.model.clans.clanMember.ClanMemberList
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWar
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWarLog
import io.github.maicolantali.types.api.model.clans.clanwarleaguegroup.ClanWarLeagueGroup
import io.github.maicolantali.types.api.model.goldpass.GoldPassSeason
import io.github.maicolantali.types.api.model.labels.LabelList
import io.github.maicolantali.types.api.model.leagues.league.League
import io.github.maicolantali.types.api.model.leagues.league.LeagueList
import io.github.maicolantali.types.api.model.leagues.leagueseason.LeagueSeasonList
import io.github.maicolantali.types.api.model.leagues.simpleleague.SimpleLeague
import io.github.maicolantali.types.api.model.leagues.simpleleague.SimpleLeagueList
import io.github.maicolantali.types.api.model.locations.location.Location
import io.github.maicolantali.types.api.model.locations.location.LocationList
import io.github.maicolantali.types.api.model.locations.ranking.clan.ClanBuilderBaseRankingList
import io.github.maicolantali.types.api.model.locations.ranking.clan.ClanCapitalRankingList
import io.github.maicolantali.types.api.model.locations.ranking.clan.ClanRankingList
import io.github.maicolantali.types.api.model.locations.ranking.player.PlayerBuilderBaseRankingList
import io.github.maicolantali.types.api.model.locations.ranking.player.PlayerRankingList
import io.github.maicolantali.types.api.model.players.player.Player
import io.github.maicolantali.types.internal.configuration.ClientConfiguration
import io.github.maicolantali.util.API_BASE_URL
import io.github.maicolantali.util.encodeTag
import io.github.maicolantali.util.getConfiguredRequestHandler
import io.github.maicolantali.util.level
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import mu.KLogging

/**
 * Client class is used to interact with the Clash of Clans API.
 *
 * This class provides methods to interact with the Clash of Clans API
 * by making HTTP requests using the provided [requestHandler].
 * It encapsulates the logic for making specific API calls and handling the response data.
 *
 * @param email The email used for authentication.
 * @param password The password used for authentication.
 * @param clientConfiguration The configuration for the client.
 */
open class Client(
    internal open val email: String,
    internal open val password: String,
    clientConfiguration: ClientConfiguration.() -> Unit = {},
) {
    internal companion object : KLogging()

    internal val config = ClientConfiguration().apply(clientConfiguration)

    init {
        logger("io.github.maicolantali").level = config.logging.clientLogLevel
    }

    private val requestHandler by lazy { getConfiguredRequestHandler() }

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
     * @return A [Deferred] [ClanList] object containing a list of clans that match the search criteria.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun searchClan(searchClanQuery: SearchClanQuery): Deferred<ClanList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans?${searchClanQuery.createQuery()}")
        }
    }

    /**
     * Gets the Clan War League group for the clan specified by [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the Clan War League group.
     * @return A [Deferred] [ClanWarLeagueGroup] object representing the Clan War League group for the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLeagueGroup(clanTag: String): Deferred<ClanWarLeagueGroup> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar/leaguegroup")
        }
    }

    /**
     * Gets the Clan War League war specified by [warTag].
     *
     * @param warTag The tag of the Clan War League war to retrieve.
     * @return A [Deferred] [ClanWar] object representing the Clan War League war with the specified tag.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested war is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLeagueWar(warTag: String): Deferred<ClanWar> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clanwarleagues/wars/${encodeTag(warTag)}")
        }
    }

    /**
     * Retrieves the current war of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the current war status.
     * @return A [Deferred] [ClanWar] object representing the current war status of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or does not have an ongoing war.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanCurrentWar(clanTag: String): Deferred<ClanWar> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/currentwar")
        }
    }

    /**
     * Retrieves detailed information about a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the detailed information.
     * @return A [Deferred] [Clan] object representing the detailed information of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClan(clanTag: String): Deferred<Clan> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}")
        }
    }

    /**
     * Retrieves the war log of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the war log.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanWarLog] object representing the war log of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no war log data available.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanWarLog(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanWarLog> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/warlog?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of members in a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the member list.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanMemberList] object representing the list of members in the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no members.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanMembers(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanMemberList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/members?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the capital raid seasons of a clan based on the provided [clanTag].
     *
     * @param clanTag The tag of the clan for which to retrieve the capital raid seasons.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanCapitalRaidSeasons] object representing the capital raid seasons of the specified clan.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested clan is not found or has no capital raid season data available.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanCapitalRaidSeasons(
        clanTag: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanCapitalRaidSeasons> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/clans/${encodeTag(clanTag)}/capitalraidseasons?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves detailed information about a player based on the provided [playerTag].
     *
     * @param playerTag The tag of the player for which to retrieve the information.
     * @return A [Deferred] [Player] object representing detailed information about the specified player.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested player is not found or has no available information.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getPlayer(playerTag: String): Deferred<Player> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/players/${encodeTag(playerTag)}")
        }
    }

    /**
     * Retrieves a list of capital leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeagueList] object representing the list of capital leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCapitalLeagues(pagination: PaginationQuery = PaginationQuery()): Deferred<SimpleLeagueList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/capitalleagues?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves detailed information about a specific capital league based on the provided [leagueId].
     *
     * @param leagueId The ID of the capital league for which to retrieve the information.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeague] object representing detailed information about the specified capital league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested capital league is not found or has no available information.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCapitalLeague(
        leagueId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<SimpleLeague> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/capitalleagues/$leagueId?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves a list of leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [LeagueList] object representing the list of leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagues(pagination: PaginationQuery = PaginationQuery()): Deferred<LeagueList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/leagues?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves detailed information about a specific league based on the provided [leagueId].
     *
     * @param leagueId The ID of the league for which to retrieve the information.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [League] object representing detailed information about the specified league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeague(
        leagueId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<League> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/leagues/$leagueId?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves a list of seasons for a specific league based on the provided [leagueId].
     *
     * @param leagueId The ID of the league for which to retrieve the list of seasons.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [LeagueSeasonList] object representing the list of seasons for the specified league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagueSeasons(
        leagueId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<LeagueSeasonList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the ranking of players for a specific league season based on the provided [leagueId] and [seasonId].
     *
     * @param leagueId The ID of the league for which to retrieve the ranking.
     * @param seasonId The ID of the season for which to retrieve the ranking.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [PlayerRankingList] object representing the ranking of players for the specified league season.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league or season or its ranking is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLeagueSeasonRanking(
        leagueId: String,
        seasonId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<PlayerRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/leagues/$leagueId/seasons/$seasonId?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of Builder Base leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeagueList] object representing the list of Builder Base leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getBuilderBaseLeagues(pagination: PaginationQuery = PaginationQuery()): Deferred<SimpleLeagueList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/builderbaseleagues?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves information about a specific Builder Base league based on the provided [leagueId].
     *
     * @param leagueId The ID of the Builder Base league to retrieve.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeague] object representing the specified Builder Base league.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getBuilderBaseLeague(
        leagueId: Int,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<SimpleLeague> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/builderbaseleagues/$leagueId?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of War Leagues.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeagueList] object representing the list of War Leagues.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getWarLeagues(pagination: PaginationQuery = PaginationQuery()): Deferred<SimpleLeagueList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/warleagues?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves information about a specific War League based on the provided [leagueId].
     *
     * @param leagueId The ID of the War League to retrieve.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [SimpleLeague] object representing the specified War League.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested league is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getWarLeague(
        leagueId: Int,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<SimpleLeague> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/warleagues/$leagueId?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of available locations.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [LocationList] object representing the list of available locations.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocations(pagination: PaginationQuery = PaginationQuery()): Deferred<LocationList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves information about a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location to retrieve.
     * @return A [Deferred] [Location] object representing the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocation(locationId: String): Deferred<Location> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId")
        }
    }

    /**
     * Retrieves the list of clans ranked in a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanRankingList] object representing the list of clans ranked in the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationClanRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of players ranked in a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the player rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [PlayerRankingList] object representing the list of players ranked in the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationPlayerRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<PlayerRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of clans
     * ranked in the Builder Base for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Builder Base clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanBuilderBaseRankingList] object representing the list of clans ranked in the Builder Base
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationClanBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanBuilderBaseRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/clans-builder-base?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of players
     * ranked in the Builder Base for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Builder Base player rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [PlayerBuilderBaseRankingList] object representing the list of players ranked in the Builder Base
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */

    suspend fun getLocationPlayerBuilderBaseRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<PlayerBuilderBaseRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/players-builder-base?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the list of clans ranked in the Capital for a specific location based on the provided [locationId].
     *
     * @param locationId The ID of the location for which to retrieve the Capital clan rankings.
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     * @return A [Deferred] [ClanCapitalRankingList] object representing the list of clans ranked in the Capital
     *         for the specified location.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws NotFoundException If the requested location is not found.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getLocationCapitalRanking(
        locationId: String,
        pagination: PaginationQuery = PaginationQuery(),
    ): Deferred<ClanCapitalRankingList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/locations/$locationId/rankings/capitals?${pagination.createQuery()}")
        }
    }

    /**
     * Retrieves the current Gold Pass season information.
     *
     * @return A [Deferred] [GoldPassSeason] object representing the current Gold Pass season information.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getCurrentGoldPass(): Deferred<GoldPassSeason> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/goldpass/seasons/current")
        }
    }

    /**
     * Retrieves a list of player labels.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     *
     * @return A [Deferred] [LabelList] containing the list of player labels.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getPlayerLabels(pagination: PaginationQuery = PaginationQuery()): Deferred<LabelList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/labels/players?$pagination.createQuery()")
        }
    }

    /**
     * Retrieves a list of clan labels.
     *
     * @param pagination An optional [PaginationQuery] object for customizing the pagination of the results.
     *
     * @return A [Deferred] [LabelList] containing the list of clan labels.
     *
     * @throws MaintenanceException If the API is in maintenance.
     * @throws BadGatewayException If the API returns an unexpected gateway exception.
     */
    suspend fun getClanLabels(pagination: PaginationQuery = PaginationQuery()): Deferred<LabelList> {
        return CoroutineScope(Dispatchers.IO).async {
            requestHandler.request("$API_BASE_URL/labels/clans?${pagination.createQuery()}")
        }
    }
}
