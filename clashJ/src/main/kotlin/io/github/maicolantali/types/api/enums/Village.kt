package io.github.maicolantali.types.api.enums

import com.google.gson.annotations.SerializedName

/**
 * Represents the *Village* model of the Clash of Clans API.
 * The Village enum class defines the type of village: Home Village or Builder Base.
 */
enum class Village {
    /**
     * The Home Village.
     */
    @SerializedName("home")
    HOME_VILLAGE,

    /**
     * The Builder Base.
     */
    @SerializedName("builderBase")
    BUILDER_BASE,
}
