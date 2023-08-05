package com.clashj.model.clan.component

import com.google.gson.annotations.SerializedName

/**
 * Represents the *WarResult* enum class of the Clash of Clans API.
 */
enum class WarResult {

    /**
     * The clan lost the war.
     */
    @SerializedName("lose")
    LOSE,

    /**
     * The clan won the war.
     */
    @SerializedName("win")
    WIN,

    /**
     * The war resulted in a tie.
     */
    @SerializedName("tie")
    TIE;
}