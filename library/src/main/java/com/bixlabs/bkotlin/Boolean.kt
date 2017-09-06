package com.bixlabs.bkotlin

/**
 * Returns false if this boolean is null
 */
fun Boolean?.orFalse(): Boolean = this ?: false