package com.clashj.http.throttler

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Throttler implementation based on a batch execution rate.
 *
 * Allow [rateLimit] requests per second before sleeping for a specific [sleepTime].
 *
 * @param rateLimit The maximum number of executions allowed within the specified time interval.
 * @param sleepTime The time interval in milliseconds between batches of executions.
 */
class BatchThrottler(
    private val rateLimit: Int = 10,
    private val sleepTime: Long = 1000
): BaseThrottler {

    private var executionCount = 0
    private val mutex = Mutex()

    /**
     * Suspend the execution until the throttling conditions are met.
     *
     * Limit the number of executions to the specified [rateLimit] within the given [sleepTime] interval.
     */
    override suspend fun wait() {
        mutex.withLock {
            if (executionCount++ >= rateLimit) {
                delay(sleepTime)
                executionCount = 0
            }
        }
    }
}