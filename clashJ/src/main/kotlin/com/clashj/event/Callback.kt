package com.clashj.event

/**
 * Represents a callback function that can be invoked for events.
 *
 * @param T1 The type of the first parameter in the callback function.
 * @param T2 The type of the second parameter in the callback function.
 * @param T3 The type of the third parameter in the callback function.
 *
 * @property singleArg A callback function that accepts one parameter of type [T1].
 * @property simple A callback function that accepts two parameters of types [T1] and [T2].
 * @property withArg A callback function that accepts three parameters of types [T1], [T2], and [T3].
 */
class Callback<T1 : Any, T2 : Any, T3 : Any>(
    val singleArg: (suspend (T1) -> Unit)? = null,
    val simple: (suspend (T1, T2) -> Unit)? = null,
    val withArg: (suspend (T1, T2, T3) -> Unit)? = null
)
