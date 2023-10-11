package com.clashj.event

import com.clashj.model.clan.Clan
import com.clashj.model.clan.component.ClanMember

/**
 * Sealed class representing specific clan-related events.
 */
sealed class ClanEvents : Event<Clan, Clan, Clan, ClanMember>() {

    /**
     * Event fires when a member joins the clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.MemberJoin) { cached, current, member ->
     *     // ...
     * }
     * ```
     */
    data object MemberJoin : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            val cachedMemberTags = cachedData.memberList.map { it.tag }.toSet()

            if (cachedMemberTags.isNotEmpty()) {
                currentData.memberList
                    .filter { it.tag !in cachedMemberTags }
                    .forEach { callback.withArg?.invoke(cachedData, currentData, it) }
            }
        }
    }

    /**
     * Event fires when a member leaves the clan.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.MemberLeft) { cached, current, member ->
     *     // ...
     * }
     * ```
     */
    data object MemberLeft : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            val currentMemberTags = currentData.memberList.map { it.tag }.toSet()

            if (currentMemberTags.isNotEmpty()) {
                cachedData.memberList
                    .filter { it.tag !in currentMemberTags }
                    .forEach { callback.withArg?.invoke(cachedData, currentData, it) }
            }
        }
    }

    /**
     * Event fires when the clan's war league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarLeague) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarLeague : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warLeague != currentData.warLeague) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's capital league changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.CapitalLeague) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object CapitalLeague : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.capitalLeague != currentData.capitalLeague) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's war frequency changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarFrequency) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarFrequency : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warFrequency != currentData.warFrequency) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's level changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ClanLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanLevel : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanLevel != currentData.clanLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's war win streak changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarWinStreak) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarWinStreak : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warWinStreak != currentData.warWinStreak) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's number of wars wins changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarWins) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarWins : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warWins != currentData.warWins) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's number of war ties changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarTies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarTies : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warTies != currentData.warTies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's number of war losses changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.WarLosses) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object WarLosses : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.warLosses != currentData.warLosses) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's points change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ClanPoints) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanPoints : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanPoints != currentData.clanPoints) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the required Town Hall level for join the clan changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.RequiredTownhallLevel) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object RequiredTownhallLevel : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.requiredTownhallLevel != currentData.requiredTownhallLevel) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's chat language changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ChatLanguage) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ChatLanguage : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.chatLanguage != currentData.chatLanguage) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's family-friendly status changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.IsFamilyFriendly) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object IsFamilyFriendly : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.isFamilyFriendly != currentData.isFamilyFriendly) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    data object RequiredTrophies : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.requiredTrophies != currentData.requiredTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's required Builder Base trophies to join the clan change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.RequiredBuilderBaseTrophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object RequiredBuilderBaseTrophies : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.requiredBuilderBaseTrophies != currentData.requiredBuilderBaseTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's required Versus Trophies to join the clan change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.RequiredVersusTrophies) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object RequiredVersusTrophies : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.requiredVersusTrophies != currentData.requiredVersusTrophies) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's war log visibility changes (public or private).
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.IsWarLogPublic) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object IsWarLogPublic : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.isWarLogPublic != currentData.isWarLogPublic) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's Builder Base points change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ClanBuilderBasePoints) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanBuilderBasePoints : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanBuilderBasePoints != currentData.clanBuilderBasePoints) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's Versus Points change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ClanVersusPoints) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanVersusPoints : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanVersusPoints != currentData.clanVersusPoints) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's Clan Capital points change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.ClanCapitalPoints) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanCapitalPoints : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanCapitalPoints != currentData.clanCapitalPoints) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's labels change.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Labels) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Labels : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.labels != currentData.labels) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's location changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Location) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Location : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.location != currentData.location) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's type changes (open, inviteOnly, closed).
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Type) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Type : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.type != currentData.type) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's number of members changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Members) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Members : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.members != currentData.members) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    data object Description : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.description != currentData.description) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's description changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Description) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object ClanCapital : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.clanCapital != currentData.clanCapital) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }

    /**
     * Event fires when the clan's badge URL changes.
     *
     * Usage Example:
     * ```kotlin
     * eventClient.registerClanCallback(MonitoredEvent.ClanEvents.Badge) { cached, current ->
     *     // ...
     * }
     * ```
     */
    data object Badge : ClanEvents() {
        override suspend fun checkAndFireCallback(
            cachedData: Clan,
            currentData: Clan,
            callback: Callback<Clan, Clan, ClanMember>
        ) {
            if (cachedData.badgeUrls != currentData.badgeUrls) {
                callback.simple?.invoke(cachedData, currentData)
            }
        }
    }
}