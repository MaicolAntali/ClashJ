package com.clashj.model.clan.component

import com.google.gson.annotations.SerializedName

/**
 * Represents the *WarState* enum class of the Clash of Clans API.
 */
enum class WarState {
    /**
     * The clan was not found in the war state.
     */
    @SerializedName("clanNotFound")
    CLAN_NOT_FOUND,

    /**
     * The access to the war state was denied (**Private war log**).
     */
    @SerializedName("accessDenied")
    ACCESS_DENIED,

    /**
     * The clan is not currently in a war.
     */
    @SerializedName("notInWar")
    NOT_IN_WAR,

    /**
     * The clan is in the matchmaking process, searching for an opponent.
     */
    @SerializedName("inMatchmaking")
    IN_MATCHMAKING,

    /**
     * The clan is about to enter the war.
     */
    @SerializedName("enterWar")
    ENTER_WAR,

    /**
     * The clan has been matched with an opponent.
     */
    @SerializedName("matched")
    MATCHED,

    /**
     * The clan is in the preparation phase of the war.
     */
    @SerializedName("preparation")
    PREPARATION,

    /**
     * The clan is currently in the war phase.
     */
    @SerializedName("war")
    WAR,

    /**
     * The clan is participating in an ongoing war.
     */
    @SerializedName("inWar")
    IN_WAR,

    /**
     * The war has ended for the clan.
     */
    @SerializedName("ended")
    ENDED,
}
