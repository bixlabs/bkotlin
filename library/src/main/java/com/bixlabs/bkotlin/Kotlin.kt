package com.bixlabs.bkotlin

/**
 * Little infix to make 'or' more readable
 */
infix fun Int.with(x: Int) = this.or(x)

/**
 * Calls the specified function block with this value as its argument and returns Unit.
 */
inline fun <R> R.withValue(block: (R) -> Unit) = this.also { block.invoke(it) }