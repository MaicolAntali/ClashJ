package io.github.maicolantali.exception

/**
 * Thrown when an HTTP 404 occurs, indicating that the requested resources are missing.
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
class NotFoundException(message: String?) : HttpException(message)
