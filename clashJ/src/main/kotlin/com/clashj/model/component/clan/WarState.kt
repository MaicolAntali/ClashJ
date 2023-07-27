package com.clashj.model.component.clan

import com.google.gson.annotations.SerializedName

/**
 * Represents the state of a ClanWar.
 */
enum class WarState {

    /**
     * Clan isn't found in the war.
     */
    @SerializedName("clanNotFound")
    CLAN_NOT_FOUND,

    /**
     * Access to the war is denied.
     */
    @SerializedName("accessDenied")
    ACCESS_DENIED,

    /**
     * Not participating in the war.
     */
    @SerializedName("notInWar")
    NOT_IN_WAR,

    /**
     * In the matchmaking phase of war.
     */
    @SerializedName("inMatchmaking")
    IN_MATCHMAKING,

    /**
     * Entered the war.
     */
    @SerializedName("enterWar")
    ENTER_WAR,

    /**
     * Matched with an opponent in war.
     */
    @SerializedName("matched")
    MATCHED,

    /**
     * In the preparation phase of war.
     */
    @SerializedName("preparation")
    PREPARATION,

    /**
     * In an active war.
     */
    @SerializedName("war")
    WAR,

    /**
     * Currently participating in the war.
     */
    @SerializedName("inWar")
    IN_WAR,

    /**
     * The war has ended.
     */
    @SerializedName("ended")
    ENDED
}
