package com.clashj.exception

/**
 * Thrown when an HTTP 503 occurs, indicating that servers are in maintenance mode.
 *
 * @param message[String] A descriptive message explaining the cause of the exception.
 */
class MaintenanceException(message: String?) : HttpException(message)
