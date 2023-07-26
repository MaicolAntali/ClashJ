package com.clashj.http.throttler

/**
 * Interface for implementing throttling behavior.
 *
 * Define a method for implementing throttling behavior ([wait]).
 */
interface BaseThrottler {

    /**
     * Suspend the execution until the throttling conditions are met.
     *
     * This method allows suspending the execution of the calling coroutine until the throttling
     * conditions are met, based on the throttler's specific implementation.
     */
    suspend fun wait()
}