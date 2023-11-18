package io.github.maicolantali.types.internal.configuration

/**
 * Configuration class for API key settings.
 *
 * @property keyName The name of the API key. Default is "clashJKey".
 * @property keyDescription The optional description for the API key. Default is null.
 * @property keyCount The number of API keys to be used. Default is 1.
 */
data class KeyConfiguration(
    var keyName: String = "clashJKey",
    var keyDescription: String? = null,
    var keyCount: Int = 1,
)
