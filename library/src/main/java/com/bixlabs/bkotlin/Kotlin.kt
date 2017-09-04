package com.bixlabs.bkotlin

// Little infix to make 'or' more readable...
infix fun Int.with(x: Int) = this.or(x)