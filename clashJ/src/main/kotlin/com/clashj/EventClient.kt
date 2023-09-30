package com.clashj

import com.clashj.event.Callback
import com.clashj.event.ClanEvents
import com.clashj.event.Event
import com.clashj.event.PlayerEvents
import com.clashj.event.cache.CacheManager
import com.clashj.exception.MaintenanceException
import com.clashj.http.RequestHandler
import com.clashj.model.clan.Clan
import com.clashj.model.clan.component.ClanMember
import com.clashj.model.player.Player
import com.clashj.util.adjustTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

/**
 * Client for managing and monitoring events related to players and clans.
 *
 * This class allows you to register callbacks for specific events and periodically
 * update player data to detect changes. It uses a coroutine-based approach for asynchronous
 * event handling.
 *
 * @param requestHandler The request handler used to make API requests.
 * @param nThread The number of threads for the internal coroutine dispatcher.
 */
class EventClient(
    private val requestHandler: RequestHandler,
    private val nThread: Int,
    private val pollingInterval: Long = 10_000,
    private val maintenanceCheckInterval: Long = 60_000
) : Client(requestHandler) {
    private val dispatcher = Executors.newFixedThreadPool(nThread).asCoroutineDispatcher()
    private var job: Job? = null
    private var isApiInMaintenance = false

    private val eventCallbacks = HashMap<Class<*>, HashMap<Event<*, *>, MutableList<Callback<*, *, *>>>>()

    private val cache = CacheManager()
    private val players: MutableList<String> = mutableListOf()
    private val clans: MutableList<String> = mutableListOf()

    private companion object {
        private val log = LoggerFactory.getLogger(RequestHandler::class.java)
    }

    /**
     * Starts polling for events and updating player data.
     *
     * Cancels any previous polling job and launches a new coroutine to periodically
     * update player data and trigger-registered callbacks.
     */
    fun startPolling() {
        job?.cancel()
        job = CoroutineScope(dispatcher).launch {
            launch { maintenanceCheckRunner() }
            launch { updaterRunner(players, ::getPlayer, Player::class.java, PlayerEvents::class.java) }
            launch { updaterRunner(clans, ::getClan, Clan::class.java, ClanEvents::class.java) }
        }
    }

    /**
     * Adds the player tag to the update queue.
     * Once the player tag is added to the queue, the client starts to monitor it.
     *
     * @param playerTag The player tag that will be monitored.
     */
    fun addPlayerToUpdateQueue(playerTag: String) {
        log.info("Adding a the player tag: $playerTag in the update queue.")
        players.add(adjustTag(playerTag))
    }

    /**
     * Adds the clan tag to the update queue.
     * Once the clan tag is added to the queue, the client starts to monitor it.
     *
     * @param clanTag The clan tag that will be monitored.
     */
    fun addClanToUpdateQueue(clanTag: String) {
        log.info("Adding a the clan tag: $clanTag in the update queue.")
        clans.add(adjustTag(clanTag))
    }

    /**
     * Removes the player tag from the update queue.
     * Once the player tag is removed from the queue, the client stops to monitor it.
     *
     * @param playerTag The player tag that will be no longer monitored.
     */
    fun removePlayerToUpdateQueue(playerTag: String) {
        log.info("Removing a the player tag: $playerTag from the update queue.")
        players.remove(adjustTag(playerTag))
    }

    /**
     * Removes the clan tag from the update queue.
     * Once the clan tag is removed from the queue, the client stops to monitor it.
     *
     * @param clanTag The clan tag that will be no longer monitored.
     */
    fun removeClanToUpdateQueue(clanTag: String) {
        log.info("Removing a the clan tag: $clanTag from the update queue.")
        players.remove(adjustTag(clanTag))
    }

    /**
     * Registers a callback for player-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerPlayerCallback(
        event: PlayerEvents,
        callback: (Player, Player) -> Unit
    ) {
        registerCallback(PlayerEvents::class.java, event, Callback<Player, Player, Nothing>(simple = callback))
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
        callback: (Player, Player, String) -> Unit
    ) {
        registerCallback(PlayerEvents::class.java, event, Callback(withArg = callback))
    }

    /**
     * Registers a callback for clan-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerClanCallback(
        event: ClanEvents,
        callback: (Clan, Clan) -> Unit
    ) {
        registerCallback(ClanEvents::class.java, event, Callback<Clan, Clan, Nothing>(simple = callback))
    }

    /**
     * Registers a callback for clan-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking two [Clan] objects
     * and an additional [ClanMember] as parameters.
     */
    fun registerClanCallback(event: ClanEvents, callback: (Clan, Clan, ClanMember) -> Unit) {
        registerCallback(ClanEvents::class.java, event, Callback(withArg = callback))
    }


    private fun registerCallback(eventType: Class<*>, event: Event<*, *>, callback: Callback<*, *, *>) {
        log.info("Adding a new callback for the $event event.")

        eventCallbacks
            .computeIfAbsent(eventType) { HashMap() }
            .computeIfAbsent(event) { mutableListOf(callback) }
    }

    private suspend fun maintenanceCheckRunner() = coroutineScope {
        launch {
            while (true) {
                try {
                    getPlayer("#2P2RG0ULV").await()

                    if (isApiInMaintenance) {
                        log.info("API is back online. Resuming updates.")
                    }
                } catch (e: MaintenanceException) {
                    if (!isApiInMaintenance) {
                        log.info("API is in maintenance. Stopping updates.")
                        isApiInMaintenance = true // API is in maintenance
                    }
                } catch (e: Exception) {
                    log.error("Error checking API maintenance status: ${e.message}")
                }

                delay(maintenanceCheckInterval)
            }
        }
    }

    private suspend fun <T> updaterRunner(
        tags: List<String>,
        apiCall: suspend (String) -> Deferred<T>,
        type: Class<T>,
        eventType: Class<*>
    ) = coroutineScope {
        while (true) {
            if (!isApiInMaintenance) {
                var delayMillis = 100L // Initial delay for exponential backoff
                tags.map { tag ->
                    async {
                        try {
                            val currentData: T = apiCall(tag).await()
                            val cacheKeyName = "${type.simpleName}_$tag"

                            if (cache.containsKey(cacheKeyName)) {
                                val cachedData = cache.getFromCache(cacheKeyName, type)!!
                                triggerEvent(cachedData, currentData, eventType)
                            }
                            cache.updateCache(cacheKeyName, currentData)

                            delayMillis = 100L // Reset delay on successful request
                        } catch (e: MaintenanceException) {
                            log.info("API is in maintenance. Stopping updates.")
                            isApiInMaintenance = true
                        } catch (e: Exception) {
                            log.error("Error: ${e.message}")
                            delay(delayMillis) // Exponential backoff
                            delayMillis *= 2
                        }
                    }
                }.awaitAll()
            }
            delay(pollingInterval)
        }
    }

    private suspend fun <T> triggerEvent(cached: T, current: T, eventType: Class<*>) = coroutineScope {
        eventCallbacks[eventType]
            ?.forEach { (event, callbacks) ->
                launch {
                    when {
                        event is PlayerEvents && cached is Player && current is Player -> {
                            callbacks
                                .filterIsInstance<Callback<Player, Player, String>>()
                                .map { event.checkAndFireCallback(cached, current, it) }
                        }

                        event is ClanEvents && cached is Clan && current is Clan -> {
                            callbacks
                                .filterIsInstance<Callback<Clan, Clan, ClanMember>>()
                                .map { event.checkAndFireCallback(cached, current, it) }
                        }
                    }
                }
            }
    }
}
