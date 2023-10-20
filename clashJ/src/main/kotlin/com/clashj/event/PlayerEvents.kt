package com.clashj.event

import com.clashj.model.player.Player

/**
 * Sealed class representing specific player-related monitored events.
 */
sealed class PlayerEvents : Event<Player, Player, Player, String>() {

    /**
     * Event fires when a player joins a clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.JoinClan) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object JoinClan : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.clan == null && currentData.clan != null) {
                callback.simple?.invoke(cachedData, currentData)
            } else if (cachedData.clan != null && currentData.clan != null && cachedData.clan != currentData.clan) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player leaves a clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.LeftClan) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object LeftClan : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.clan != null && currentData.clan == null) {
                callback.simple?.invoke(cachedData, currentData)
            } else if (cachedData.clan != null && cachedData.clan != currentData.clan) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.League) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object League : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.league != currentData.league) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Builder Base league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderBaseLeague) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BuilderBaseLeague : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.builderBaseLeague != currentData.builderBaseLeague) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's role within a clan changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ClanMemberRole) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object ClanMemberRole : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.role != currentData.role) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's war preference changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.WarPreference) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object WarPreference : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.warPreference != currentData.warPreference) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object AttackWins : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.attackWins != currentData.attackWins) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object DefenseWins : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.defenseWins != currentData.defenseWins) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object VersusTrophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.versusTrophies != currentData.versusTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's number of attacks wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.AttackWins) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BestVersusTrophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.bestVersusTrophies != currentData.bestVersusTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Town Hall level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.TownHallLevel) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object TownHallLevel : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.townHallLevel != currentData.townHallLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Town Hall weapon level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.TownHallWeaponLevel) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object TownHallWeaponLevel : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.townHallWeaponLevel != currentData.townHallWeaponLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's number of Versus Battles wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.VersusBattleWins) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object VersusBattleWins : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.versusBattleWins != currentData.versusBattleWins) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Legend statistics change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.LegendStatistics) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object LegendStatistics : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.legendStatistics != currentData.legendStatistics) {
                callback.simple?.invoke(cachedData, currentData)
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
     * eventClient.registerPlayerCallback(PlayerEvents.Troops) { cachedData, currentData, troopName ->
     *     // ...
     * }
     * ```
     */
    data object Troops : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            currentData.troops
                .filter { it !in cachedData.troops }
                .forEach { callback.withArg?.invoke(cachedData, currentData, it.name) }
        }
    }

    /**
     * Event fires when new heroes become available or existing heroes level up in a player's profile.
     *
     * **Note**: The callback uses also as a parameter the `heroName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Heroes) { cachedData, currentData, heroName ->
     *     // ...
     * }
     * ```
     */
    data object Heroes : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            currentData.heroes
                .filter { it !in cachedData.heroes }
                .forEach { callback.withArg?.invoke(cachedData, currentData, it.name) }
        }
    }

    /**
     * Event fires when new spells become available or existing spells level up in a player's profile.
     *
     * **Note**: The callback uses also as a parameter the `spellName`
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Spells) { cachedData, currentData, spellName ->
     *     // ...
     * }
     * ```
     */
    data object Spells : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            currentData.spells
                .filter { it !in cachedData.spells }
                .forEach { callback.withArg?.invoke(cachedData, currentData, it.name) }
        }
    }

    /**
     * Event fires when player labels change in a player's profile.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Labels) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object Labels : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.labels != currentData.labels) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the player's name changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Name) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object Name : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.name != currentData.name) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the player's experience level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ExpLevel) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object ExpLevel : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.expLevel != currentData.expLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the player's trophy count changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Trophies) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object Trophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.trophies != currentData.trophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's best trophy count changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BestTrophies) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BestTrophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.bestTrophies != currentData.bestTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player donates troops.
     *
     * Example usage:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.Donations) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object Donations : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.donations < currentData.donations) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player receives troops.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.DonationsReceive) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object DonationsReceive : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.donationsReceived < currentData.donationsReceived) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Builder Hall level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderHallLevel) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BuilderHallLevel : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.builderHallLevel != currentData.builderHallLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's Builder Base trophies change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BuilderBaseTrophies) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BuilderBaseTrophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.builderBaseTrophies != currentData.builderBaseTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's best Builder Base trophies change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.BestBuilderBaseTrophies) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object BestBuilderBaseTrophies : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.bestBuilderBaseTrophies != currentData.bestBuilderBaseTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's war stars change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.WarStarts) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object WarStarts : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.warStars != currentData.warStars) {
                callback.simple?.invoke(cachedData, currentData)
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
     * eventClient.registerPlayerCallback(PlayerEvents.Achievements) { cachedData, currentData, achievementsName ->
     *     // ...
     * }
     * ```
     */
    data object Achievements : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            currentData.achievements
                .filter { it !in cachedData.achievements }
                .forEach { callback.withArg?.invoke(cachedData, currentData, it.name) }
        }
    }

    /**
     * Event fires when a player's clan capital contributions change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.ClanCapitalContributions) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object ClanCapitalContributions : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.clanCapitalContributions != currentData.clanCapitalContributions) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when a player's player house changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerPlayerCallback(PlayerEvents.PlayerHouse) { cachedData, currentData ->
     *     // ...
     * }
     * ```
     */
    data object PlayerHouse : PlayerEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Player,
            currentData: Player,
            callback: Callback<Player, Player, String>
        ) {
            if (cachedData.playerHouse != currentData.playerHouse) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }
}