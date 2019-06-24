package com.bixlabs.bkotlin

import android.content.res.TypedArray

/**
 * Calls the specified function [block] with this [TypedArray] as its receiver and then
 * recycles the same to be re-used by a later caller.
 *
 * After calling this function you must not ever touch the typed array again.
 */
fun TypedArray.useAndRecycle(block: TypedArray.() -> Unit) {
    block(this)
    this.recycle()
}