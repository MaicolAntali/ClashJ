package com.clashj.event

import com.clashj.model.clan.Clan
import com.clashj.model.clan.component.ClanMember

sealed class ClanEvents : Event<Clan, ClanMember>() {

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

}