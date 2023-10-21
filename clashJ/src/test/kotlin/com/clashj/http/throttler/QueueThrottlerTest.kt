package com.clashj.http.throttler

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class QueueThrottlerTest {
    @Test
    fun `should sleep for 0 millis`() {
        runBlocking {
            val throttler = QueueThrottler(1000)

            val elapsed =
                measureTimeMillis {
                    throttler.wait()
                }

            assertThat(elapsed).isLessThan(100)
        }
    }
}
