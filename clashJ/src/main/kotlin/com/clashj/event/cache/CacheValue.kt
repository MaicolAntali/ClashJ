package com.clashj.event.cache

import com.clashj.model.clan.Clan
import com.clashj.model.player.Player

/**
 * Encapsulate different types to enable a type-safe update and retrieval from the cache.
 * Comprises two data subclasses:
 * - [PlayerType]
 * - [ClanType]
 */
sealed class CacheValue {

    /**
     * Represents a cached player object.
     *
     * @property value The [Player] object stored in the cache.
     */
    data class PlayerType(val value: Player) : CacheValue()

    /**
     * Represents a cached clan object.
     *
     * @property value The [Clan] object stored in the cache.
     */
    data class ClanType(val value: Clan) : CacheValue()
}