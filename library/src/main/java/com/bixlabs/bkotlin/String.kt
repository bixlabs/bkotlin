package com.bixlabs.bkotlin

fun String.Companion.EMPTY (): String = ""

fun String.isNotBlackOrEmpty(): Boolean = !this.isEmpty() || !all { it.isWhitespace() }