package com.clashj.util

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SafeSetTest {

    @Test
    fun `should add 5 elements`() {
        runBlocking {
            val safeSet = SafeSet<Int>()

            (1..5).map {
                launch {
                    safeSet.add(it)
                }
            }.joinAll()

            assertThat(safeSet.size()).isEqualTo(5)
            assertThat(safeSet.isEmpty()).isFalse()
        }
    }

    @Test
    fun `should remove 2 elements`() {
        runBlocking {
            val safeSet = SafeSet(setOf(1, 2, 3))

            (2..3).map {
                launch {
                    safeSet.remove(it)
                }
            }.joinAll()

            assertThat(safeSet.size()).isEqualTo(1)
            assertThat(safeSet.contains(1)).isTrue()
        }
    }

    @Test
    fun `should be empty`() {
        runBlocking {
            val safeSet = SafeSet<Int>()

            assertThat(safeSet.isEmpty()).isTrue()
        }
    }

    @Test
    fun `should not add existing elements`() {
        runBlocking {
            val safeSet = SafeSet(setOf(1, 2, 3))

            (1..3).map {
                launch {
                    safeSet.add(it)
                }
            }.joinAll()

            assertThat(safeSet.size()).isEqualTo(3)
        }
    }

    @Test
    fun `should return an infinity loop of the elements`() {
        runBlocking {
            val safeSet = SafeSet(setOf(1, 2))

            assertThat(safeSet.next()).isEqualTo(1)
            assertThat(safeSet.next()).isEqualTo(2)
            assertThat(safeSet.next()).isEqualTo(1)
            assertThat(safeSet.next()).isEqualTo(2)

            assertThat(safeSet.size()).isEqualTo(2)
        }
    }
}
