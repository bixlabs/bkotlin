package com.bixlabs.bkotlin

/**
 * Returns Zero (0) if it is null
 */
fun Number?.orZero(): Number = if (this != null) this else 0

/**
 * Returns Zero (0) if it is null
 */
fun Int?.orZero(): Int = if (this != null) this else 0

/**
 * Returns Zero (0) if it is null
 */
fun Float?.orZero(): Float = if (this != null) this else 0.0f

/**
 * Returns Zero (0) if it is null
 */
fun Double?.orZero(): Double = if (this != null) this else 0.0