package com.bixlabs.bkotlin

import android.util.Patterns

/**
 * Short for String foo = ""
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