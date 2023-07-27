package com.clashj.model.component.clan

import com.google.gson.annotations.SerializedName

enum class WarState {

    @SerializedName("clanNotFound")
    CLAN_NOT_FOUND,

    @SerializedName("accessDenied")
    ACCESS_DENIED,

    @SerializedName("notInWar")
    NOT_IN_WAR,

    @SerializedName("inMatchmaking")
    IN_MATCHMAKING,

    @SerializedName("enterWar")
    ENTER_WAR,

    @SerializedName("matched")
    MATCHED,

    @SerializedName("preparation")
    PREPARATION,

    @SerializedName("war")
    WAR,

    @SerializedName("inWar")
    IN_WAR,

    @SerializedName("ended")
    ENDED
}
