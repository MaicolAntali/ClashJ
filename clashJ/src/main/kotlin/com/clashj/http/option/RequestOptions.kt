package com.clashj.http.option

import io.ktor.http.HttpMethod

/**
 * Representing the options for customizing an HTTP request.
 *
 * @param method The HTTP method to be used for the request. Default is [HttpMethod.Get].
 *
 * @param body The request body as a JSON string. Default is `null`.
 *
 * @param maxRetryAttempts The maximum number of retry attempts in case of request timeouts.
 * Default is 4.
 * If the request times out, the method will retry the request with increasing delays between retries.
 *
 * @param ignoreThrottler Specifies whether to ignore the throttler when making the request.
 * Default is `false`.
 * If `false`, the method will wait for throttling based on the configured throttler before executing the request.
 * If `true`, the throttler will be bypassed, and the request will be executed immediately.
 */
data class RequestOptions(
    val method: HttpMethod = HttpMethod.Get,
    val body: String? = null,
    val maxRetryAttempts: Int = 4,
    val ignoreThrottler: Boolean = false
)
