package io.github.maicolantali.http.throttler

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Throttler implementation based on a fixed time interval between executions.
 *
 * @param sleepTime The time interval in milliseconds between consecutive executions.
 */
class QueueThrottler(
    private val sleepTime: Long = 100,
) : BaseThrottler {
    private var lastRun: Long = 0
    private val mutex = Mutex()

    /**
     * Suspend the execution until the throttling conditions are met.
     *
     * Allow suspending the execution of the calling coroutine until the throttling
     * conditions are met, based on the fixed time interval between consecutive executions.
     */
    override suspend fun wait() {
        mutex.withLock {
            val millisToSleep = sleepTime - (System.currentTimeMillis() - lastRun)

            if (millisToSleep > 0) {
                delay(millisToSleep)
            }

            lastRun = System.currentTimeMillis()
        }
    }
}
