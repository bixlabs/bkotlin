package com.bixlabs.bkotlin

import android.util.Patterns
import java.util.regex.Pattern

/**
 * Short for `foo: String = ""`
 */
fun String.Companion.EMPTY(): String = ""

/**
 * Returns true if this string is not empty nor composed by whitespaces
 */
fun String.isNotBlankOrEmpty(): Boolean = !this.isEmpty() || !all { it.isWhitespace() }

/**
 * Returns true if this string is a valid URL
 */
fun String.isValidUrl() : Boolean = Patterns.WEB_URL.matcher(this.toLowerCase()).matches()

/**
 * Returns true if this string contains exactly the provided string.
 * This method uses RegEx to evaluate and its case-sensitive. What makes it different from the classic
 * [contains] is that it doesn't uses [indexOf], hence it's more performant when used on long char sequences
 * and has much higher probabilities of not returning false positives per approximation.
 */
fun String.containsExact(string: String): Boolean =
        Pattern.compile("(?<=^|[^a-zA-Z0-9])\\Q$string\\E(?=\$|[^a-zA-Z0-9])")
                .matcher(this)
                .find()