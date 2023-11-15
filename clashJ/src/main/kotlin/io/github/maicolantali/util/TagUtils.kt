@file:JvmName("TagUtils")

package io.github.maicolantali.util

/**
 * Adjusts the provided tag to make it suitable for use in the Clash of Clans API.
 *
 * Example usage:
 * ```
 * val adjusted = adjustTag("1     2   3 AB  c   d")
 * // adjusted will be: "#123ABCD"
 * ```
 *
 * @param tag The tag string to be adjusted.
 * @param prefix The optional prefix to be added to the adjusted tag. Default is "#."
 * @return The adjusted tag with the prefix.
 */
fun adjustTag(
    tag: String,
    prefix: String = "#",
): String {
    return prefix +
        Regex("[^A-Z0-9]+")
            .replace(tag.uppercase(), "")
            .replace('O', '0')
}

/**
 * Encodes the provided tag for use in URLs.
 *
 * Example usage:
 * ```
 * val encoded = encodeTag("#1 2   3 ab  C")
 * // encoded will be: "%23123ABC"
 * ```
 *
 * @param tag The tag string to be encoded.
 * @return The encoded tag with the "%23" prefix.
 */
fun encodeTag(tag: String): String {
    return adjustTag(tag, "%23")
}
