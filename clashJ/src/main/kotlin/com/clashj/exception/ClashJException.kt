package com.clashj.exception

/**
 * Default exception for the ClashJ library.
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
open class ClashJException(message: String?) : RuntimeException(message)
