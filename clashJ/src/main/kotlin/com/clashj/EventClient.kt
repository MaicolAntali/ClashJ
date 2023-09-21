package com.clashj

import com.clashj.event.EventCallback
import com.clashj.event.MonitoredEvent
import com.clashj.event.PlayerEvents
import com.clashj.event.cache.CacheManager
import com.clashj.exception.MaintenanceException
import com.clashj.http.RequestHandler
import com.clashj.model.player.Player
import com.clashj.util.adjustTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap
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
    requestHandler: RequestHandler,
    nThread: Int
) : Client(requestHandler) {
    private val dispatcher = Executors.newFixedThreadPool(nThread).asCoroutineDispatcher()
    private var job: Job? = null

    private val eventCallbacks = ConcurrentHashMap<MonitoredEvent<*, *>, MutableList<EventCallback>>()

    private val cache = CacheManager()
    private val players: MutableList<String> = mutableListOf()

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
            launch { updaterRunner { playerUpdater() } }
        }
    }

    /**
     * Adds the player tag to the update queue.
     * Once the player tag is added to the queue, the client starts to monitor it.
     *
     * @param playerTag The player tag that will be monitored.
     */
    fun addPlayerToMonitoredEvent(playerTag: String) {
        log.info("Adding a the player tag: $playerTag in the update queue.")
        players.add(adjustTag(playerTag))
    }

    /**
     * Removes the player tag from the update queue.
     * Once the player tag is removed from the queue, the client stops to monitor it.
     *
     * @param playerTag The player tag that will be no longer monitored.
     */
    fun removePlayerToMonitoredEvent(playerTag: String) {
        log.info("Removing a the player tag: $playerTag from the update queue.")
        players.remove(adjustTag(playerTag))
    }

    /**
     * Registers a callback for player-related events.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs.
     */
    fun registerPlayerCallback(
        event: MonitoredEvent<Player, EventCallback.PlayerCallback>,
        callback: (Player, Player) -> Unit
    ) {
        registerCallback(event, EventCallback.PlayerCallback.PlayerSimpleCallback(callback))
    }

    /**
     * Registers a callback for player-related events with an iterable callback function.
     *
     * @param event The monitored event for which the callback is registered.
     * @param callback The callback function to be invoked when the event occurs, taking two [Player] objects
     * and an additional [String] identifier as parameters.
     */
    fun registerPlayerCallback(
        event: MonitoredEvent<Player, EventCallback.PlayerCallback>,
        callback: (Player, Player, String) -> Unit
    ) {
        registerCallback(event, EventCallback.PlayerCallback.PlayerIterableCallback(callback))
    }

    private fun registerCallback(event: MonitoredEvent<*, *>, eventCallback: EventCallback) {
        log.info("Adding a new callback for the $event event.")
        eventCallbacks.computeIfAbsent(event) { mutableListOf() }.add(eventCallback)
    }

    private suspend fun updaterRunner(updaterFun: suspend () -> Unit) = coroutineScope {
        while (true) {
            updaterFun.invoke()
            delay(10_000)
        }
    }

    private suspend fun playerUpdater() = coroutineScope {
        players.mapIndexed { index, playerTag ->
            async {
                delay(2L * index)
                val currentPlayer: Player = try {
                    getPlayer(playerTag).await()
                } catch (e: MaintenanceException) {
                    println("ERROR: MaintenanceException, $e")
                    return@async
                }

                if (cache.containsKey(playerTag)) {
                    val cachedPlayer = cache.getFromCache(playerTag, Player::class.java)!!
                    processPlayerEvents(cachedPlayer, currentPlayer)
                }
                cache.updateCache(playerTag, currentPlayer)
            }
        }.awaitAll()
    }

    private suspend fun processPlayerEvents(cachedPlayer: Player, currentPlayer: Player) = coroutineScope {
        eventCallbacks.forEach { (event, callbacks) ->
            if (event is PlayerEvents) {
                launch {
                    callbacks
                        .filterIsInstance<EventCallback.PlayerCallback>()
                        .forEach { callback ->
                            event.fireCallback(cachedPlayer, currentPlayer, callback)
                        }
                }
            }
        }
    }
}
