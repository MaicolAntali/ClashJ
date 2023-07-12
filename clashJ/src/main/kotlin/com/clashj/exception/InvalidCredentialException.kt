package com.clashj.exception

/**
 * Thrown when an HTTP 403 occurs, indicating that credentials are invalid.
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
class InvalidCredentialException(message: String?) : HttpException(message)
