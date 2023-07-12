package com.clashj.exception

/**
 * Thrown when an HTTP error occurs.
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
open class HttpException(message: String?) : ClashJException(message)
