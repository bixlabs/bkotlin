package com.bixlabs.bkotlin

/**
 * Returns class name. Useful for Log Tags
 */
val Any.TAG: String
    get() = this::class.java.simpleName