package io.github.maicolantali.http.option

/**
 * Represents the options for generating keys in the developer site.
 *
 * @property keyName The name for the generated keys.
 * @property keyDescription The optional description for the generated keys. Can be null.
 * @property keyCount The number of keys to generate.
 */
data class KeyOptions(
    val keyName: String,
    val keyDescription: String?,
    val keyCount: Int,
)
