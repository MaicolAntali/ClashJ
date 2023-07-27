package com.clashj.model.component.clan

import com.google.gson.annotations.SerializedName

/**
 * Represents a Clash of Clans API ClanMemberRole.
 */
enum class ClanMemberRole {
    /**
     * The player is not a member of the clan.
     */
    @SerializedName("notMember")
    NOT_MEMBER,

    /**
     * The player is a regular member of the clan.
     */
    @SerializedName("member")
    MEMBER,

    /**
     * The player is an admin in the clan.
     */
    @SerializedName("admin")
    ADMIN,

    /**
     * The player is a co-leader in the clan.
     */
    @SerializedName("coLeader")
    CO_LEADER,

    /**
     * The player is the leader of the clan.
     */
    @SerializedName("leader")
    LEADER;
}