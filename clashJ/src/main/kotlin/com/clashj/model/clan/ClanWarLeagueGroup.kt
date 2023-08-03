package com.clashj.model.clan

import com.clashj.model.common.Badge
import com.google.gson.annotations.SerializedName

data class ClanWarLeagueGroup(
    val tag: String,
    val state: ClanWarLeagueGroupState,
    val season: String,
    val clans: List<ClanWarLeagueClan>,
    val rounds: List<ClanWarLeagueRound>
)

enum class ClanWarLeagueGroupState {
    @SerializedName("groupNotFound")
    GROUP_NOT_FOUND,

    @SerializedName("notInWar")
    NOT_IN_WAR,

    @SerializedName("preparation")
    PREPARATION,

    @SerializedName("war")
    WAR,

    @SerializedName("ended")
    ENDED
}

data class ClanWarLeagueClan(
    val tag: String,
    val clanLevel: Int,
    val name: String,
    val members: List<ClanWarLeagueClanMember>,
    val badgeUrls: Badge
)

data class ClanWarLeagueClanMember(
    val tag: String,
    val townHallLevel: Int,
    val name: String
)

data class ClanWarLeagueRound(
    val warTags: List<String>
)