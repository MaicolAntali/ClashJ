package com.clashj.event

sealed class Event<DataType : Any, ArgType : Any> {
    abstract suspend fun checkAndFireCallback(
        cachedData: DataType,
        currentData: DataType,
        callback: Callback<DataType, DataType, ArgType>
    )
}