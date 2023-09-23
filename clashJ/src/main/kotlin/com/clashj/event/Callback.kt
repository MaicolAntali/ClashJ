package com.clashj.event

class Callback<T1 : Any, T2 : Any, T3 : Any>(
    val simple: (suspend (T1, T2) -> Unit)? = null,
    val withArg: (suspend (T1, T2, T3) -> Unit)? = null,
)