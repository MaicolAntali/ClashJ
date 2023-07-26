package com.clashj.http.throttler

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class BatchThrottlerTest {
    @Test
    fun `should sleep for 0 millis`() {
        runBlocking {
            val throttler = BatchThrottler()

            val elapsed = measureTimeMillis {
                throttler.wait()
            }

            Assertions.assertThat(elapsed).isLessThan(100)
        }
    }

    @Test
    fun `should sleep for more than 400 mills`() {
        runBlocking {
            val throttler = BatchThrottler(2, 200)

            val elapsed = measureTimeMillis {
                // 0 millis
                throttler.wait()
                throttler.wait()
                // 200 millis
                throttler.wait()
                throttler.wait()
                // 400 millis
                throttler.wait()
                throttler.wait()
            }

            Assertions.assertThat(elapsed).isGreaterThan(400)
        }
    }
}