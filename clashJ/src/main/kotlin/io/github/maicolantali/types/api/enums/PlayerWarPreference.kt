package io.github.maicolantali.types.api.enums

import com.google.gson.annotations.SerializedName

/**
 * Represents the *PlayerWarPreference* model of the Clash of Clans API.
 * The PlayerWarPreference enum class defines a player's war preference status.
 */
enum class PlayerWarPreference {
    /**
     * The player is participating in wars.
     */
    @SerializedName("in")
    IN,

    /**
     * The player is not participating in wars.
     */
    @SerializedName("out")
    OUT,
}
