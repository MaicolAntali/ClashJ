package com.clashj.model.component

/**
 * Represents a Clash of Clans API Icon.
 *
 * @param name The name of the label.
 * @param id The ID of the label.
 * @param iconUrls The [Icon] object containing icon URLs for different sizes.
 */
data class Label(
    val name: String,
    val id: Int,
    val iconUrls: Icon
)
