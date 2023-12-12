package io.github.maicolantali.types.internal.configuration

import io.github.maicolantali.http.throttler.BaseThrottler
import io.github.maicolantali.http.throttler.BatchThrottler

/**
 * Configuration class for client settings.
 *
 * @property key The configuration for API keys.
 * @property httpClient The configuration for HTTP settings.
 * @property event The configuration for event handling.
 * @property throttler The throttling strategy to be used by the client.
 */
data class ClientConfiguration(
    internal var key: KeyConfiguration = KeyConfiguration(),
    internal var httpClient: HttpConfiguration = HttpConfiguration(),
    internal var logging: LoggingConfiguration = LoggingConfiguration(),
    internal var event: EventConfiguration = EventConfiguration(),
    internal var throttler: BaseThrottler = BatchThrottler(),
) {
    /**
     * Configures key using the settings specified in [KeyConfiguration].
     */
    fun key(config: KeyConfiguration.() -> Unit) {
        key.config()
    }

    /**
     * Configures the HTTP settings for the client.
     *
     * @param config Lambda function to configure the [HttpConfiguration].
     */
    fun httpClient(config: HttpConfiguration.() -> Unit) {
        httpClient.config()
    }

    /**
     * Configures logging using the settings specified in [LoggingConfiguration].
     */
    fun logging(config: LoggingConfiguration.() -> Unit) {
        logging.config()
    }

    /**
     * Configures events using the settings specified in [EventConfiguration].
     */
    fun event(config: EventConfiguration.() -> Unit) {
        event.config()
    }
}
