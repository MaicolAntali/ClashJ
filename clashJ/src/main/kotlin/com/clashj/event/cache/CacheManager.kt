package com.clashj.event.cache

import com.clashj.model.clan.Clan
import com.clashj.model.player.Player
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages a cache of various data [types][CacheValue] using key-value pairs.
 *
 * This class provides methods to update, retrieve, and check the presence of cached data
 * with support for different data types such as [Player] and [Clan].
 *
 * The internal cache using a [ConcurrentHashMap] to store data.
 */
class CacheManager {

    private val cache = ConcurrentHashMap<String, CacheValue>()

    /**
     * Updates the cache
     *
     * This method accepts data of generic type [T] and associates it with the provided key in the cache.
     * The data type is determined at runtime and must be either [Player] or [Clan].
     *
     * @param key The key to associate with the cached data.
     * @param data The data to be cached. Must be of type [Player] or [Clan].
     *
     * @throws IllegalArgumentException if an unsupported data type is provided.
     */
    fun <T> updateCache(key: String, data: T) {
        val cacheValue = when (data) {
            is Player -> CacheValue.PlayerType(data)
            is Clan -> CacheValue.ClanType(data)
            else -> throw IllegalArgumentException("Unsupported data type")
        }

        cache[key] = cacheValue
    }

    /**
     * Retrieves data from the cache based on the provided key and data type.
     *
     * @param key The key associated with the cached data.
     * @param clazz The [Class] object representing the expected data type.
     * @return The cached data of the specified type or `null` if not found or if the data type is unsupported.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getFromCache(key: String, clazz: Class<T>): T? {
        val cachedValue = cache[key] ?: return null

        return when (clazz) {
            Player::class.java -> (cachedValue as? CacheValue.PlayerType)?.value as T
            Clan::class.java -> (cachedValue as? CacheValue.ClanType)?.value as T
            else -> null
        }
    }

    /**
     * Checks if the cache contains data associated with the provided key.
     *
     * @param key The key to check for in the cache.
     * @return `true` if the cache contains data associated with the key, `false` otherwise.
     */
    fun containsKey(key: String): Boolean {
        return cache.containsKey(key)
    }
}