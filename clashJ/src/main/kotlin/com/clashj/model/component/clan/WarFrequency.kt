package com.clashj.model.component.clan

import com.google.gson.annotations.SerializedName

/**
 * Represents a Clash of Clans API WarFrequency.
 */
enum class WarFrequency{
    /**
     * The war frequency is unknown or not specified.
     */
    @SerializedName("unknown")
    UNKNOWN,

    /**
     * The clan is at war always.
     */
    @SerializedName("always")
    ALWAYS,

    /**
     * The clan is at war more than once per week.
     */
    @SerializedName("moreThanOncePerWeek")
    MORE_THAN_ONCE_PER_WEEK,

    /**
     * The clan is at war once per week.
     */
    @SerializedName("OncePerWeek")
    ONCE_PER_WEEK,

    /**
     * The clan is at war less than once per week.
     */
    @SerializedName("LessThanOncePerWeek")
    LESS_THAN_ONCE_PER_WEEK,

    /**
     * The clan is not at war (never at war).
     */
    @SerializedName("never")
    NEVER,

    /**
     * The war frequency can be any or is not restricted.
     */
    @SerializedName("any")
    ANY

}
