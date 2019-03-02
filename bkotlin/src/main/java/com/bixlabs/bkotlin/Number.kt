package com.bixlabs.bkotlin

/**
 * Returns Zero (0) if this number is null
 */
fun Number?.orZero(): Number = this ?: 0

/**
 * Returns true if this number is null or zero (0)
 */
fun Number?.isNullOrZero(): Boolean = this == null || this == 0

/**
 * Returns true if this number is not null or zero (0)
 */
fun Int?.isNotNullAndMoreThanZero(): Boolean = this != null && this > 0

/**
 * Returns true if this number is not null or zero (0)
 */
fun Long?.isNotNullAndMoreThanZero(): Boolean = this != null && this > 0L

/**
 * Returns true if this number is not null or zero (0)
 */
fun Float?.isNotNullAndMoreThanZero(): Boolean = this != null && this > 0F

/**
 * Returns true if this number is not null or zero (0)
 */
fun Double?.isNotNullAndMoreThanZero(): Boolean = this != null && this > 0.0

/**
 * Loops through this number, executing the given function [action] every time.
 *
 * A zero-based index of current iteration is passed as a parameter to [action].
 */
inline fun Number.times(action: (Int) -> Unit) = repeat(this.toInt(), action)