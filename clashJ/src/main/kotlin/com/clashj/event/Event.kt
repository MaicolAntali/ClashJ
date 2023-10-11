package com.clashj.event

/**
 * Represents an abstract event that can be monitored for specific data types and additional arguments.
 *
 * @param DataType The type of data that will be monitored.
 * @param CallbackT1 The type of the first parameter in the callback function.
 * @param CallbackT2 The type of the second parameter in the callback function.
 * @param CallbackT3 The type of additional argument (if any) for callback functions.
 */
sealed class Event<DataType : Any, CallbackT1 : Any, CallbackT2 : Any, CallbackT3 : Any> {
    /**
     * Abstract function to check and trigger a callback based on the provided data and callback.
     *
     * @param cachedData The cached data of type DataType to be compared.
     * @param currentData The current data of type DataType to be compared.
     * @param callback The callback function to be invoked when the event occurs.
     */
    abstract suspend fun checkAndFireCallback(
        cachedData: DataType,
        currentData: DataType,
        callback: Callback<CallbackT1, CallbackT2, CallbackT3>
    )
}
