package io.github.maicolantali.exception

/**
 * Thrown when an HTTP 500, 502 or 504 occurs
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
class BadGatewayException(message: String?) : HttpException(message)
