package com.bixlabs.bkotlin

/**
 * Returns false if this boolean is null
 */
fun Boolean?.orFalse(): Boolean = this ?: false

/**
 * Determines if the value of this optional Boolean is not null and equals to `true`
 * @return `true` if this optonal boolean is not null and `true`, `false` if this Boolean is null or `false`.
 */
fun Boolean?.isTrue(): Boolean = if (this == null) false else this == true

/**
 * Determines if the value of this optional Boolean is not null and equals to `false`
 * @return `true` if this optonal boolean is not null and `false`, `false` if this Boolean is null or `false`.
 */
fun Boolean?.isFalse(): Boolean = if (this == null) false else this == false