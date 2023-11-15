package io.github.maicolantali.event.cache

import io.github.maicolantali.model.clan.Clan
import io.github.maicolantali.model.clan.ClanWar
import io.github.maicolantali.model.player.Player

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

    /**
     * Represents a cached war object.
     *
     * @property value The [ClanWar] object stored in the cache.
     */
    data class ClanWarType(val value: ClanWar) : CacheValue()
}
