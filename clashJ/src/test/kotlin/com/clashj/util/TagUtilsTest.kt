package com.clashj.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TagUtilsTest {
    @Nested
    inner class AdjustTag {
        @Test
        fun `should remove all whitespaces`() {
            val fixed = adjustTag("     123    ABC     ")

            assertThat(fixed).isEqualTo("#123ABC")
            assertThat(fixed).doesNotContainAnyWhitespaces()
        }

        @Test
        fun `should uppercase all letters`() {
            val fixed = adjustTag("123aBc")

            assertThat(fixed).isEqualTo("#123ABC")
        }

        @Test
        fun `should add a custom prefix`() {
            val fixed = adjustTag("#123aBc", "!")

            assertThat(fixed).isEqualTo("!123ABC")
        }
    }

    @Nested
    inner class EncodeTag {
        @Test
        fun `should replace # with percent23`() {
            val encoded = encodeTag("#  aB  c   12  3")

            assertThat(encoded).isEqualTo("%23ABC123")
            assertThat(encoded).doesNotContainAnyWhitespaces()
        }
    }
}
