package com.bixlabs.bkotlin

/**
 * Returns false if it is null
 */
fun Boolean?.orFalse(): Boolean = this ?: false