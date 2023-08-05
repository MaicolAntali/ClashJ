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
 * Builder class for constructing instances of the [Client] class with customizable options.
 *
 * To construct a [Client] instance, first, create a [ClientBuilder] with the required login credentials,
 * and then use the provided methods to customize other options. Finally, call the [build] function
 * to obtain the configured [Client] instance.
 * ```
 * val client = ClientBuilder("email", "pwd").keyName("fooBar").build()
 * ```
 *
 * @property email The email associated with the Clash of Clans API developer account.
 * @property password The password associated with the Clash of Clans API developer account.
 * @property keyName The default name for the API key. Defaults to `clashJKey`.
 * @property keyDescription The optional description for the API key.
 * @property keyCount The number of API keys to be generated. Defaults to `1`.
 * @property engine The HttpClientEngine to be used for HTTP requests. Defaults to `Apache5.create()`.
 * @property connectionTimeout The connection timeout for HTTP requests in milliseconds. Defaults to `15000`ms (15 seconds).
 * @property requestTimeout The request timeout for HTTP requests in milliseconds. Defaults to `15000`ms (15 seconds).
 * @property throttler The `BaseThrottler` instance responsible for controlling the rate of API requests. Defaults to [BatchThrottler].
 */
data class ClientBuilder(
    val email: String,
    val password: String,

    // Key options
    var keyName: String = "clashJKey",
    var keyDescription: String? = null,
    var keyCount: Int = 1,

    // Engine & engine options
    var engine: HttpClientEngine = Apache5.create(),
    var connectionTimeout: Long = 15_000,
    var requestTimeout: Long = 15_000,

    // Throttler
    var throttler: BaseThrottler = BatchThrottler()

) {

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
    fun build(): Client {
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
}
