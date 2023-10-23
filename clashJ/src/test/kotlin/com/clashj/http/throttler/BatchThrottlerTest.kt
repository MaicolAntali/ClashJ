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

            val elapsed =
                measureTimeMillis {
                    throttler.wait()
                }

            Assertions.assertThat(elapsed).isLessThan(100)
        }
    }
}
