package com.clashj.util

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SafeSetTest {

    @Test
    fun `should add 5 elements`() = runBlocking {
        val safeSet = SafeSet<Int>()

        (1..5).map {
            launch {
                safeSet.add(it)
            }
        }.joinAll()

        assertEquals(5, safeSet.size())
        assertFalse(safeSet.isEmpty())
    }

    @Test
    fun `should remove 2 elements`() = runBlocking {
        val safeSet = SafeSet(setOf(1, 2, 3))

        (2..3).map {
            launch {
                safeSet.remove(it)
            }
        }.joinAll()

        assertEquals(1, safeSet.size())
        assertTrue(safeSet.contains(1))
    }

    @Test
    fun `should be empty`() = runBlocking {
        val safeSet = SafeSet<Int>()

        assertTrue(safeSet.isEmpty())
    }

    @Test
    fun `should not add existing elements`() = runBlocking {
        val safeSet = SafeSet(setOf(1, 2, 3))

        (1..3).map {
            launch {
                safeSet.add(it)
            }
        }.joinAll()

        assertEquals(3, safeSet.size())
    }
}
