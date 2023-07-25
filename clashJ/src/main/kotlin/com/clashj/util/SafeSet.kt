package com.clashj.util

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A thread-safe **set** implementation.
 *
 * This class provides a thread-safe set data structure that allows adding, removing,
 * checking containment, retrieving the size, and checking the emptiness of the set.
 *
 * @param c The initial collection to populate the set. Defaults to an empty set if not provided.
 *
 * @param T The type of elements in the set.
 */
class SafeSet<T>(
    c: Collection<T> = emptySet()
) {
    private val set: HashSet<T> = HashSet(c)
    private val mutex: Mutex = Mutex()

    private var index: Int = 0

    /**
     * Adds an element to the set.
     *
     * This method adds the specified element to the set in a thread-safe approach.
     *
     * @param element The element to be added.
     * @return `true` if the element was added successfully, `false` if it already exists in the set.
     */
    suspend fun add(element: T): Boolean {
        mutex.withLock { return set.add(element) }
    }

    /**
     * Removes an element from the set.
     *
     * This method removes the specified element from the set in a thread-safe approach.
     *
     * @param element The element to be removed.
     * @return `true` if the element was removed successfully, `false` if it doesn't exist in the set.
     */
    suspend fun remove(element: T): Boolean {
        mutex.withLock { return set.remove(element) }
    }

    /**
     * Checks if the set contains the specified element.
     *
     * This method checks if the set contains the specified element in a thread-safe approach.
     *
     * @param element The element to check for containment.
     * @return `true` if the element is found in the set, `false` otherwise.
     */
    suspend fun contains(element: T): Boolean {
        mutex.withLock { return set.contains(element) }
    }

    /**
     * Retrieves the next element in the set infinitely.
     *
     * This method retrieves the next element in the set in a thread-safe approach.
     *
     * @return The next element in the set, or `null` if the set is empty.
     */
    suspend fun next(): T {
        mutex.withLock {
            val element = set.elementAt(index)
            index = if ((index + 1) >= set.size) 0 else index + 1

            return element
        }
    }

    /**
     * Removes all the elements from this set.
     */
    suspend fun clear() {
        mutex.withLock { set.clear() }
    }

    /**
     * Retrieves the current size of the set.
     *
     * This method returns the number of elements in the set in a thread-safe approach.

     * @return The size of the set.
     */
    suspend fun size(): Int {
        mutex.withLock { return set.size }
    }

    /**
     * Checks if the set is empty.
     *
     * This method checks if the set is empty in a thread-safe approach.
     *
     * @return `true` if the set is empty, `false` otherwise.
     */
    suspend fun isEmpty(): Boolean {
        mutex.withLock { return set.isEmpty() }
    }
}
