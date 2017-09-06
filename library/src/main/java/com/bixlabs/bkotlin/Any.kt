package com.bixlabs.bkotlin

/**
 * Returns the class name. Useful for Log Tags
 */
val Any.TAG: String
    get() = this::class.java.simpleName