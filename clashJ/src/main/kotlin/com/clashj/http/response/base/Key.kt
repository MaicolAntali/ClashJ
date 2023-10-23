package com.clashj.http.response.base

/**
 * Represents a Clash of Clans developer API key.
 *
 * Encapsulates the information related to a [key], including its [id]], [name],
 * a list of IP ([cidrRanges]), and the actual [key] value.
 *
 * @property id The unique identifier of the key.
 * @property name The name of the key.
 * @property cidrRanges A list of IP addresses associated with the key.
 * @property key The actual value of the key.
 */
data class Key(
    val id: String,
    val name: String,
    val cidrRanges: List<String>,
    val key: String,
)
