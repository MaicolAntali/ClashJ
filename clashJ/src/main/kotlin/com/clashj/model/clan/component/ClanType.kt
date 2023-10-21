package com.clashj.model.clan.component

import com.google.gson.annotations.SerializedName

/**
 * Represents the *ClanType* enum class of the Clash of Clans API.
 */
enum class ClanType {
    /**
     * An open clan
     */
    @SerializedName("open")
    OPEN,

    /**
     * An invite-only clan
     */
    @SerializedName("inviteOnly")
    INVITE_ONLY,

    /**
     * A closed clan
     */
    @SerializedName("closed")
    CLOSED,
}
