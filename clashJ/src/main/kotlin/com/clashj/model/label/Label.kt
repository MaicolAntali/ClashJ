package com.clashj.model.label

import com.clashj.model.common.Icon

/**
 * Represents the *Label* model of the Clash of Clans API.
 *
 * @property name The name of the label.
 * @property id The unique identifier for the label.
 * @property iconUrls The icon URLs representing the label's icon in different sizes.
 */
data class Label(
    val name: String,
    val id: Int,
    val iconUrls: Icon
)
