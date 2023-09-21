package com.clashj.event

import com.clashj.model.player.Player

/**
 * Sealed class representing specific player-related monitored events.
 */
sealed class PlayerEvents : MonitoredEvent<Player, EventCallback.PlayerCallback>() {

    /**
     * Event fires when a player joins a clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.JoinClan) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object JoinClan : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.clan == null && current.clan != null) {
                executeCallback(cached, current, callback)
            } else if (cached.clan != null && current.clan != null && cached.clan != current.clan) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player leaves a clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.LeftClan) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object LeftClan : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.clan != null && current.clan == null) {
                executeCallback(cached, current, callback)
            } else if (cached.clan != null && cached.clan != current.clan) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.League) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object League : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.league != current.league) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Builder Base league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderBaseLeague) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BuilderBaseLeague : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.builderBaseLeague != current.builderBaseLeague) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's role within a clan changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ClanMemberRole) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanMemberRole : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.role != current.role) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's war preference changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.WarPreference) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarPreference : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.warPreference != current.warPreference) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object AttackWins : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.attackWins != current.attackWins) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object DefenseWins : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.defenseWins != current.defenseWins) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object VersusTrophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.versusTrophies != current.versusTrophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BestVersusTrophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.bestVersusTrophies != current.bestVersusTrophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Town Hall level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.TownHallLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object TownHallLevel : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.townHallLevel != current.townHallLevel) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Town Hall weapon level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.TownHallWeaponLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object TownHallWeaponLevel : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.townHallWeaponLevel != current.townHallWeaponLevel) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's number of Versus Battles wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.VersusBattleWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object VersusBattleWins : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.versusBattleWins != current.versusBattleWins) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Legend statistics change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.LegendStatistics) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object LegendStatistics : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.legendStatistics != current.legendStatistics) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when new troops become available or existing spells level up in a player's profile.
     *
     * **Note**: The callback uses also as a parameter the `troopName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Troops) { cached, current, troopName ->
     *     // ...
     * }
     * ```
     */
    data object Troops : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            current.troops
                .filter { it !in cached.troops }
                .forEach { executeCallback(cached, current, callback, it.name) }
        }
    }

    /**
     * Event fires when new heroes become available or existing heroes level up in a player's profile.
     *
     * **Note**: The callback uses also as a parameter the `heroName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Heroes) { cached, current, heroName ->
     *     // ...
     * }
     * ```
     */
    data object Heroes : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            current.heroes
                .filter { it !in cached.heroes }
                .forEach { executeCallback(cached, current, callback, it.name) }
        }
    }

    /**
     * Event fires when new spells become available or existing spells level up in a player's profile.
     *
     * **Note**: The callback uses also as a parameter the `spellName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Spells) { cached, current, spellName ->
     *     // ...
     * }
     * ```
     */
    data object Spells : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            current.spells
                .filter { it !in cached.spells }
                .forEach { executeCallback(cached, current, callback, it.name) }
        }
    }

    /**
     * Event fires when player labels change in a player's profile.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Labels) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Labels : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.labels != current.labels) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when the player's name changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Name) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Name : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.name != current.name) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when the player's experience level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ExpLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ExpLevel : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.expLevel != current.expLevel) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when the player's trophy count changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Trophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Trophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.trophies != current.trophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's best trophy count changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BestTrophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BestTrophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.bestTrophies != current.bestTrophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player donates troops.
     *
     * Example usage:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Donations) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Donations : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.donations < current.donations) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player receives troops.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.DonationsReceive) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object DonationsReceive : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.donationsReceived < current.donationsReceived) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Builder Hall level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderHallLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BuilderHallLevel : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.builderHallLevel != current.builderHallLevel) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's Builder Base trophies change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderBaseTrophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BuilderBaseTrophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.builderBaseTrophies != current.builderBaseTrophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's best Builder Base trophies change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BestBuilderBaseTrophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object BestBuilderBaseTrophies : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.bestBuilderBaseTrophies != current.bestBuilderBaseTrophies) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's war stars change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.WarStarts) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarStarts : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.warStars != current.warStars) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player achieves a new achievement.
     *
     * **Note**: The callback uses also as a parameter the `achievementsName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Achievements) { cached, current, achievementsName ->
     *     // ...
     * }
     * ```
     */
    data object Achievements : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            current.achievements
                .filter { it !in cached.achievements }
                .forEach { executeCallback(cached, current, callback, it.name) }
        }
    }

    /**
     * Event fires when a player's clan capital contributions change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ClanCapitalContributions) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanCapitalContributions : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.clanCapitalContributions != current.clanCapitalContributions) {
                executeCallback(cached, current, callback)
            }
        }
    }

    /**
     * Event fires when a player's player house changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.PlayerHouse) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object PlayerHouse : PlayerEvents() {
        override suspend fun fireCallback(cached: Player, current: Player, callback: EventCallback.PlayerCallback) {
            if (cached.playerHouse != current.playerHouse) {
                executeCallback(cached, current, callback)
            }
        }
    }
}