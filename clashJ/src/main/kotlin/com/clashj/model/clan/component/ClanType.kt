package com.clashj.model.clan.component

import com.google.gson.annotations.SerializedName

/**
 * Represents a Clash of Clans API ClanType.
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