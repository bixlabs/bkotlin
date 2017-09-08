@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.bixlabs.bkotlin

import android.util.SparseArray
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import java.util.*
import java.util.List


/**
 * Iterate the receiver [List] backwards.
 * @param [f] an action to invoke on each list element.
 */
inline fun <T> List<T>.forEachReversed(f: (T) -> Unit) {
    var i = size - 1
    while (i >= 0) {
        f(get(i))
        i--
    }
}

/**
 * Iterate the receiver [List] backwards.
 * @param [f] an action to invoke on each list element (index, element).
 */
inline fun <T> List<T>.forEachReversedIndexed(f: (Int, T) -> Unit) {
    var i = size - 1
    while (i >= 0) {
        f(i, get(i))
        i--
    }
}

/**
 *  Iterate the receiver [SparseArray]
 *  @action an action to invoke on each key value pair
 *  @throws [ConcurrentModificationException] if modified while iterating
 */
inline fun <E> SparseArray<E>.forEach(action: (Int, E) -> Unit) {
    val size = this.size()
    for (i in 0 until size) {
        if (size != this.size()) throw ConcurrentModificationException()
        action(this.keyAt(i), this.valueAt(i))
    }
}

/**
 *  Iterate the receiver [SparseBooleanArray]
 *  @action an action to invoke on each key value pair
 *  @throws [ConcurrentModificationException] if modified while iterating
 */
inline fun SparseBooleanArray.forEach(action: (Int, Boolean) -> Unit) {
    val size = this.size()
    for (i in 0 until size) {
        if (size != this.size()) throw ConcurrentModificationException()
        action(this.keyAt(i), this.valueAt(i))
    }
}

/**
 *  Iterate the receiver [SparseIntArray]
 *  @action an action to invoke on each key value pair
 *  @throws [ConcurrentModificationException] if modified while iterating
 */
inline fun SparseIntArray.forEach(action: (Int, Int) -> Unit) {
    val size = this.size()
    for (i in 0 until size) {
        if (size != this.size()) throw ConcurrentModificationException()
        action(this.keyAt(i), this.valueAt(i))
    }
}

/**
 * Convert the Android pair to a Kotlin one.
 * @see [toAndroidPair].
 */
fun <F, S> android.util.Pair<F, S>.toKotlinPair(): Pair<F, S> {
    return first to second
}

/**
 * Convert the Kotlin pair to an Android one.
 * @see [toKotlinPair].
 */
fun <F, S> Pair<F, S>.toAndroidPair(): android.util.Pair<F, S> {
    return android.util.Pair(first, second)
}