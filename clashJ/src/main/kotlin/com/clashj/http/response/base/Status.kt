package com.clashj.http.response.base

/**
 * Represents the status information of an HTTP response.
 *
 * Encapsulates the status information, including a status [code], [message], and optional [detail].
 * It is commonly used to convey the result of an API response.
 *
 * @property code The status code indicating the outcome or status of a response.
 * @property message A descriptive message providing additional information about the status.
 * @property detail An optional detailed message providing more specific information about the status.
 */
data class Status(
    val code: Int,
    val message: String,
    val detail: String?
)
