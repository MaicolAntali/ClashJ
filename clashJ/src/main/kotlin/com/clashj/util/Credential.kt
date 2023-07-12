package com.clashj.util

/**
 * User credential used to log in into the developer website.
 *
 * @property email The email address associated with the Clash of Clans developer account.
 * @property password The password associated with the Clash of Clans developer account.
 */
data class Credential(
    val email: String,
    val password: String
)
