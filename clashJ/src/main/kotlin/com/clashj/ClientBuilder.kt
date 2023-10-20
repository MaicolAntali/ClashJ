package com.clashj

import com.clashj.http.RequestHandler
import com.clashj.http.option.EngineOptions
import com.clashj.http.option.KeyOptions
import com.clashj.http.throttler.BaseThrottler
import com.clashj.http.throttler.BatchThrottler
import com.clashj.util.Credential
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.apache5.Apache5

/**
 * Builder class for constructing instances of the [Client] or [EventClient] class with customizable options.
 *
 * To construct a client instance, first, create a [ClientBuilder] with the required login credentials,
 * and then use the provided methods to customize other options.
 * Finally, call the [buildClient] function to obtain the configured [Client] instance or,
 * [buildEventClient] function to obtain the configured [EventClient]
 * ```
 * val client = ClientBuilder("email", "pwd").keyName("fooBar").buildClient()
 * val eventClient = ClientBuilder("email", "pwd").keyName("fooBar").buildEventClient()
 * ```
 *
 * @property email The email associated with the Clash of Clans API developer account.
 * @property password The password associated with the Clash of Clans API developer account.
 */
class ClientBuilder(
    private val email: String,
    private val password: String,
) {
    // Key options
    private var keyName: String = "clashJKey"
    private var keyDescription: String? = null
    private var keyCount: Int = 1

    // Engine & engine options
    private var engine: HttpClientEngine = Apache5.create()
    private var connectionTimeout: Long = 15_000
    private var requestTimeout: Long = 15_000

    // Throttler
    private var throttler: BaseThrottler = BatchThrottler()

    /**
     * Sets the name for the generated API key.
     *
     * @param keyName The name for the API key.
     * @return This [ClientBuilder] instance.
     */
    fun keyName(keyName: String) = apply { this.keyName = keyName }

    /**
     * Sets the description for the generated API key.
     *
     * @param keyDescription The description for the API key.
     * @return This [ClientBuilder] instance.
     */
    fun keyDescription(keyDescription: String) = apply { this.keyDescription = keyDescription }

    /**
     * Sets the number of API keys to be generated.
     *
     * The maximum number of keys allowed by the Clash of Clans developer site is 10.
     *
     * @param keyCount The number of API keys to be generated.
     * @return This [ClientBuilder] instance.
     */
    fun keyCount(keyCount: Int) = apply { this.keyCount = keyCount }

    /**
     * Sets the HttpClientEngine to be used for HTTP requests.
     *
     * @param engine The HttpClientEngine to be used.
     * @return This [ClientBuilder] instance.
     */
    fun engine(engine: HttpClientEngine): ClientBuilder = apply { this.engine = engine }

    /**
     * Sets the connection timeout for HTTP requests in milliseconds.
     *
     * @param connectionTimeout The connection timeout for HTTP requests in milliseconds.
     * @return This [ClientBuilder] instance.
     */
    fun connectionTimeout(connectionTimeout: Long) = apply { this.connectionTimeout = connectionTimeout }

    /**
     * Sets the request timeout for HTTP requests in milliseconds.
     *
     * @param requestTimeout The request timeout for HTTP requests in milliseconds.
     * @return This [ClientBuilder] instance.
     */

    fun requestTimeout(requestTimeout: Long) = apply { this.requestTimeout = requestTimeout }

    /**
     * Sets the `BaseThrottler` instance responsible for controlling the rate of API requests.
     *
     * @param throttler The `BaseThrottler` instance.
     * @return This [ClientBuilder] instance.
     */
    fun throttler(throttler: BaseThrottler) = apply { this.throttler = throttler }

    /**
     * Builds a [Client] instance with the configured options.
     *
     * @return The constructed [Client] instance.
     */
    fun buildClient(): Client {
        return Client(
            RequestHandler(
                Credential(this.email, this.password),
                KeyOptions(this.keyName, this.keyDescription, this.keyCount),
                EngineOptions(this.connectionTimeout, this.requestTimeout),
                engine = this.engine,
                throttler = this.throttler
            )
        )
    }

    /**
     * Builds an [EventClient] instance with the configured options.
     *
     * @param nThread The number of threads for event handling (default is 3).
     * @param pollingInterval The interval between event polling in milliseconds (default is 15,000ms).
     * @param maintenanceCheckInterval The interval for maintenance event checks in milliseconds (default is 30,000ms).
     *
     * @return The constructed [EventClient] instance.
     */
    fun buildEventClient(
        nThread: Int = 3,
        pollingInterval: Long = 15_000,
        maintenanceCheckInterval: Long = 30_000
    ): EventClient {
        return EventClient(
            RequestHandler(
                Credential(this.email, this.password),
                KeyOptions(this.keyName, this.keyDescription, this.keyCount),
                EngineOptions(this.connectionTimeout, this.requestTimeout),
                engine = this.engine,
                throttler = this.throttler
            ),
            nThread,
            pollingInterval,
            maintenanceCheckInterval
        )
    }
}
