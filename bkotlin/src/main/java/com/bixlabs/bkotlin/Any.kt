package com.bixlabs.bkotlin

/**
 * Returns the class name. Useful for Log Tags
 */
val Any.TAG: String
    get() = this::class.java.simpleName

/**
 * Calls the specified function block whenever this is `null`.
 *
 * **Note that if this is chained after a [isNotNullThen] block where that [isNotNullThen] assigns a `null` value
 * to `this` then [isNullThen] block will be called since `this` is now `null`,  so chaining can lead to unexpected behavior.**
 * This is not meant for a replacement of an `if...then` clause, but as a shortcut method to evaluate if `this` is `null`.
 */
inline fun Any?.isNullThen(callback: () -> Unit) = if (this == null) {
    callback.invoke()
} else {}


/**
 * Calls the specified function block with this value as its argument whenever this is not `null` and returns `Unit`.
 *
 * **Note that if this is chained after a [isNullThen] block where that [isNullThen] block assigns value to [T] then [isNotNullThen]
 * block will be called since [T] is no longer `null`, so chaining can lead to unexpected behavior.**
 * This is not meant for a replacement of an `if...then` clause, but as a shortcut method to evaluate if
 * [T] is not `null`.
 */
inline fun <T> T?.isNotNullThen(callback: (T) -> Unit) = if (this != null) {
    callback.invoke(this)
} else {}