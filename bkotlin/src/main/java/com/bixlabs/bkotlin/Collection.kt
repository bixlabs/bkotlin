@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.bixlabs.bkotlin

import android.util.SparseArray
import android.util.SparseBooleanArray
import android.util.SparseIntArray
import java.util.*
import kotlin.collections.HashMap


private val random = Random()

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

/**
 * True if this collection is null or empty, false otherwise
 */
fun <E> Collection<E>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

/**
 * True if this collection is not null nor empty, false otherwise
 */
fun <E> Collection<E>?.isNotNullOrEmpty() = !isNullOrEmpty()

/**
 * True if this list is null or empty, false otherwise
 */
fun <E>List<E>?.isNullOrEmpty() = this == null || this.isEmpty()


/**
 * True if this collection is not null nor empty, false otherwise
 */
fun <E>List<E>?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

/**
 * returns a random element from the list
 */
fun <E>List<E>.random() = this[random.nextInt(size)]

/**
 * returns a map of page number and the amount of items given
 */
operator fun <E>List<E>.div(amount: Int): HashMap<Int, kotlin.collections.List<E>> {
    val map = HashMap<Int, kotlin.collections.List<E>>()
    var startIndexOfCattedList = 0
    var page = 0

    for (indexOfList in 0 until this.size) {
        if ((indexOfList + 1) % amount == 0) {

            if (startIndexOfCattedList == 0) {
                map.put(page++, subList(startIndexOfCattedList, indexOfList + 1))
            } else {
                map.put(page++, subList(startIndexOfCattedList, indexOfList))
            }

            startIndexOfCattedList = indexOfList
        }
    }

    return map
}

/**
 * Adds [E] to this list if the same doesn't exist
 */
infix fun <E> ArrayList<E>.addIfNotExist(obj: E) = if (!this.contains(obj)) add(obj) else false

/**
 * Adds [E] to this list if the same doesn't previously exist.
 * so
 */
infix fun <E> MutableList<E>.addIfNotExist(obj: E) = if (!this.contains(obj)) add(obj) else false


/**
 * Adds [E] to this map if the same doesn't exist
 */
fun <K, E> HashMap<K, E>.addIfNotExist(key: K, obj: E): E? = if (!this.containsKey(key)) put(key, obj) else { null }

/**
 * Adds [E] to this map if the same doesn't exist
 */
fun <K, E> MutableMap<K, E>.addIfNotExist(key: K, obj: E): E? = if (!this.containsKey(key)) put(key, obj) else { null }