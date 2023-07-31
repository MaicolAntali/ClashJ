package com.clashj.model.component.clan

import com.google.gson.annotations.SerializedName

/**
 * Represents the result of a clan war.
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