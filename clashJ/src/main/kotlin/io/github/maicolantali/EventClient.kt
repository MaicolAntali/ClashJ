package io.github.maicolantali

import io.github.maicolantali.event.Callback
import io.github.maicolantali.event.ClanEvents
import io.github.maicolantali.event.Event
import io.github.maicolantali.event.MaintenanceEvents
import io.github.maicolantali.event.PlayerEvents
import io.github.maicolantali.event.WarEvents
import io.github.maicolantali.event.cache.CacheManager
import io.github.maicolantali.exception.MaintenanceException
import io.github.maicolantali.types.api.model.clans.clan.Clan
import io.github.maicolantali.types.api.model.clans.clanMember.ClanMember
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWar
import io.github.maicolantali.types.api.model.clans.clanwar.ClanWarAttack
import io.github.maicolantali.types.api.model.players.player.Player
import io.github.maicolantali.types.internal.configuration.ClientConfiguration
import io.github.maicolantali.util.adjustTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * Client for managing and monitoring events related to players and clans.
 *
 * This class allows you to register callbacks for specific events and periodically
 * update player data to detect changes. It uses a coroutine-based approach for asynchronous
 * event handling.
 *
 * @param email The email associated with the Clash of Clans API account.
 * @param password The password associated with the Clash of Clans API account.
 * @param clientConfiguration The configuration for the client.You can customize the client using the [ClientConfiguration] DSL.
 */
class EventClient(
    override val email: String,
    override val password: String,
    clientConfiguration: ClientConfiguration.() -> Unit = {},
) : Client(email, password, clientConfiguration) {
    // Polling job
    private var job: Job? = null

    // Callbacks
    private val eventCallbacks = HashMap<Class<*>, HashMap<Event<*, *, *, *>, MutableList<Callback<*, *, *>>>>()

    // Cache & Tags
    private val cache = CacheManager()
    private val players: MutableList<String> = mutableListOf()
    private val clans: MutableList<String> = mutableListOf()
    private val wars: MutableList<String> = mutableListOf()

    // Maintenance
    private var isApiInMaintenance = false
    private var maintenanceStartTime: LocalDateTime? = null

    /**
     * Starts polling for events and updating player data.
     *
     * Cancels any previous polling job and launches a new coroutine to periodically
     * update player data and trigger-registered callbacks.
     */
    fun startPolling() {
        job?.cancel()
        job =
            CoroutineScope(dispatcher).launch {
                launch { maintenanceCheckRunner() }
                launch { updaterRunner(players, ::getPlayer, Player::class.java, PlayerEvents::class.java) }
                launch { updaterRunner(clans, ::getClan, Clan::class.java, ClanEvents::class.java) }
                launch { updaterRunner(wars, ::getClanCurrentWar, ClanWar::class.java, WarEvents::class.java) }
            }
    }

    /**
     * Adds a player's tag to the update queue for monitoring.
     * Once a player's tag is added to the queue, the client will begin monitoring the player.
     *
     * This function is used to include a player in the update queue for real-time monitoring
     * of player-related events and data.
     *
     * @param playerTag The player tag that will be monitored and updated.
     */
    fun addPlayerToUpdateQueue(playerTag: String) {
        logger.info { "Adding player tag: $playerTag to the update queue for monitoring." }
        players.add(adjustTag(playerTag))
    }

    /**
     * Adds a clan's tag to the update queue for monitoring.
     * Once a clan's tag is added to the queue, the client will begin monitoring the clan.
     *
     * This function is used to include a clan in the update queue for real-time monitoring
     * of clan-related events and data.
     *
     * @param clanTag The clan tag that will be monitored and updated.
     */
    fun addClanToUpdateQueue(clanTag: String) {
        logger.info { "Adding the clan tag: $clanTag to the update queue for monitoring." }
        clans.add(adjustTag(clanTag))
    }

    /**
     * Adds a clan's war events to the update queue for monitoring.
     * Once a clan's war events are added to the queue, the client will start monitoring them.
     *
     * This function is used to include a clan's war events, such as clan wars and their details,
     * in the update queue for real-time monitoring.
     *
     * @param clanTag The clan tag for which war events will be monitored and updated.
     */
    fun addWarToUpdateQueue(clanTag: String) {
        logger.info { "Adding the clan tag: $clanTag to the update queue for monitoring war events." }
        wars.add(adjustTag(clanTag))
    }

    /**
     * Removes a player's tag from the update queue.
     * Once a player's tag is removed from the queue, the client stops monitoring the player.
     *
     * This function is used to exclude a player from the update queue, thereby stopping real-time
     * monitoring of player-related events and data.
     *
     * @param playerTag The player tag that will no longer be monitored.
     */
    fun removePlayerToUpdateQueue(playerTag: String) {
        logger.info { "Removing the player tag: $playerTag from the update queue." }
        players.remove(adjustTag(playerTag))
    }

    /**
     * Removes a clan's tag from the update queue.
     * Once a clan's tag is removed from the queue, the client stops monitoring the clan.
     *
     * This function is used to exclude a clan from the update queue, thereby stopping real-time
     * monitoring of clan-related events and data.
     *
     * @param clanTag The clan tag that will no longer be monitored.
     */
    fun removeClanToUpdateQueue(clanTag: String) {
        logger.info { "Removing the clan tag: $clanTag from the update queue." }
        clans.remove(adjustTag(clanTag))
    }

    /**
     * Removes a clan's war events from the update queue.
     * Once a clan's war events are removed from the queue, the client stops monitoring them.
     *
     * This function is used to exclude a clan's war events, such as clan wars and their details,
     * from the update queue, thereby stopping real-time monitoring of war-related events.
     *
     * @param clanTag The clan tag for which war events will no longer be monitored.
     */
    fun removeWarToUpdateQueue(clanTag: String) {
        logger.info { "Removing the clan tag: $clanTag from the update queue for monitoring war events." }
        wars.remove(adjustTag(clanTag))
    }

    /**
     * Registers a callback for player-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerPlayerCallback(
        event: PlayerEvents,
        callback: suspend (Player, Player) -> Unit,
    ) {
        registerCallback(event, Callback<Player, Player, Nothing>(simple = callback))
    }

    /**
     * Registers a callback for player-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking two [Player] objects
     * and an additional [String] identifier as parameters.
     */
    fun registerPlayerCallback(
        event: PlayerEvents,
        callback: suspend (Player, Player, String) -> Unit,
    ) {
        registerCallback(event, Callback(withArg = callback))
    }

    /**
     * Registers a callback for clan-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerClanCallback(
        event: ClanEvents,
        callback: suspend (Clan, Clan) -> Unit,
    ) {
        registerCallback(event, Callback<Clan, Clan, Nothing>(simple = callback))
    }

    /**
     * Registers a callback for clan-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking two [Clan] objects
     * and an additional [ClanMember] as parameters.
     */
    fun registerClanCallback(
        event: ClanEvents,
        callback: suspend (Clan, Clan, ClanMember) -> Unit,
    ) {
        registerCallback(event, Callback(withArg = callback))
    }

    /**
     * Registers a callback for war-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerWarCallback(
        event: WarEvents,
        callback: suspend (ClanWar) -> Unit,
    ) {
        registerCallback(event, Callback<ClanWar, Nothing, Nothing>(singleArg = callback))
    }

    /**
     * Registers a callback for war-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking one [ClanWar] objects
     * and an additional [ClanWarAttack] as parameters.
     */
    fun registerWarCallback(
        event: WarEvents,
        callback: suspend (ClanWar, ClanWarAttack) -> Unit,
    ) {
        registerCallback(event, Callback<ClanWar, ClanWarAttack, Nothing>(simple = callback))
    }

    /**
     * Registers a callback for maintenance-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking one [LocalDateTime] parameter.
     */
    fun registerMaintenanceCallback(
        event: MaintenanceEvents,
        callback: suspend (LocalDateTime) -> Unit,
    ) {
        registerCallback(event, Callback<LocalDateTime, Nothing, Nothing>(singleArg = callback))
    }

    /**
     * Registers a callback for maintenance-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking two [LocalDateTime] parameters.
     */
    fun registerMaintenanceCallback(
        event: MaintenanceEvents,
        callback: suspend (LocalDateTime, LocalDateTime) -> Unit,
    ) {
        registerCallback(event, Callback<LocalDateTime, LocalDateTime, Nothing>(simple = callback))
    }

    private fun registerCallback(
        event: Event<*, *, *, *>,
        callback: Callback<*, *, *>,
    ) {
        logger.info { "Registering a new callback for the ${event::class.java.simpleName} event." }
        eventCallbacks
            .computeIfAbsent(event::class.java.superclass) { HashMap() }
            .computeIfAbsent(event) { mutableListOf(callback) }

        logger.trace { "Callback registered successfully for ${event::class.java.simpleName} event." }
    }

    private suspend fun maintenanceCheckRunner() =
        coroutineScope {
            launch {
                while (true) {
                    try {
                        logger.trace { "Checking API maintenance status..." }
                        getPlayer("#2P2RG0ULV").await()

                        if (isApiInMaintenance) {
                            isApiInMaintenance = false
                            logger.info { "API is back online. Resuming updates." }

                            triggerEvent(maintenanceStartTime, LocalDateTime.now(), MaintenanceEvents::class.java)
                            maintenanceStartTime = null
                        }
                    } catch (e: MaintenanceException) {
                        if (!isApiInMaintenance) {
                            logger.info { "API is now in maintenance mode. Automatic updates temporarily paused." }
                            isApiInMaintenance = true // API is in maintenance
                        }
                        if (maintenanceStartTime == null) {
                            maintenanceStartTime = LocalDateTime.now()
                            logger.info { "Maintenance started at: $maintenanceStartTime" }
                            triggerEvent(maintenanceStartTime, eventType = MaintenanceEvents::class.java)
                        }
                    } catch (e: Exception) {
                        logger.error(e) { "Error checking API maintenance status: ${e.message}. Retrying..." }
                    }

                    logger.trace {
                        "Maintenance check iteration completed. " +
                            "Next check in ${config.event.maintenanceCheckInterval} milliseconds."
                    }
                    delay(config.event.maintenanceCheckInterval)
                }
            }
        }

    private suspend fun <T> updaterRunner(
        tags: List<String>,
        apiCall: suspend (String) -> Deferred<T>,
        type: Class<T>,
        eventType: Class<*>,
    ) = coroutineScope {
        logger.trace { "Updater loop started for ${type.simpleName}." }

        while (true) {
            if (!isApiInMaintenance) {
                var delayMillis = 100L // Initial delay for exponential backoff
                tags.map { tag ->
                    async {
                        try {
                            logger.trace { "Updating ${type.simpleName} data for tag: $tag." }
                            val currentData: T = apiCall(tag).await()
                            val cacheKeyName = "${type.simpleName}_$tag"

                            if (cache.containsKey(cacheKeyName)) {
                                val cachedData = cache.getFromCache(cacheKeyName, type)!!
                                triggerEvent(cachedData, currentData, eventType)
                            }
                            cache.updateCache(cacheKeyName, currentData)

                            delayMillis = 100L // Reset delay on successful request
                            logger.trace { "Update successful for ${type.simpleName} data: $tag." }
                        } catch (e: MaintenanceException) {
                            logger.info { "API is in maintenance. Stopping updates for ${type.simpleName}: $tag." }
                            isApiInMaintenance = true
                        } catch (e: Exception) {
                            logger.error(e) { "Error occurred while updating ${type.simpleName} data for tag: $tag." }
                            delay(delayMillis) // Exponential backoff
                            delayMillis *= 2
                        }
                    }
                }.awaitAll()
            }
            delay(config.event.pollingInterval)
        }
    }

    private suspend fun <T> triggerEvent(
        cached: T,
        current: T? = null,
        eventType: Class<*>,
    ) = coroutineScope {
        eventCallbacks[eventType]
            ?.forEach { (event, callbacks) ->
                launch {
                    when {
                        event is PlayerEvents && cached is Player && current is Player -> {
                            callbacks
                                .filterIsInstance<Callback<Player, Player, String>>()
                                .forEach { event.checkAndFireCallback(cached, current, it) }
                        }

                        event is ClanEvents && cached is Clan && current is Clan -> {
                            callbacks
                                .filterIsInstance<Callback<Clan, Clan, ClanMember>>()
                                .forEach { event.checkAndFireCallback(cached, current, it) }
                        }

                        event is WarEvents && cached is ClanWar && current is ClanWar -> {
                            callbacks
                                .filterIsInstance<Callback<ClanWar, ClanWarAttack, Nothing>>()
                                .forEach { event.checkAndFireCallback(cached, current, it) }
                        }

                        // Triggers the MaintenanceStared event
                        event is MaintenanceEvents && cached is LocalDateTime && current == null -> {
                            callbacks
                                .filterIsInstance<Callback<LocalDateTime, LocalDateTime, Nothing>>()
                                .forEach { event.checkAndFireCallback(cached, cached, it) }
                        }

                        // Triggers the MaintenanceStared event
                        event is MaintenanceEvents && cached is LocalDateTime && current is LocalDateTime -> {
                            callbacks
                                .filterIsInstance<Callback<LocalDateTime, LocalDateTime, Nothing>>()
                                .forEach { event.checkAndFireCallback(cached, current, it) }
                        }
                    }
                }
            }
    }
}
