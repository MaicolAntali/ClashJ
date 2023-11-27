package io.github.maicolantali.types.api.model.clans.clanwarleaguegroup

import com.google.gson.annotations.SerializedName

/**
 * Represents the state of a clan war league group in the Clash of Clans API.
 */
enum class ClanWarLeagueGroupState {
    /**
     * The group for clan war league is not found.
     */
    @SerializedName("groupNotFound")
    GROUP_NOT_FOUND,

    /**
     * The clan war league group is not currently in a war.
     */
    @SerializedName("notInWar")
    NOT_IN_WAR,

    /**
     * The clan war league group is in the preparation phase before the war starts.
     */
    @SerializedName("preparation")
    PREPARATION,

    /**
     * The clan war league group is currently in a war.
     */
    @SerializedName("war")
    WAR,

    /**
     * The clan war league group war has ended.
     */
    @SerializedName("ended")
    ENDED,
}
