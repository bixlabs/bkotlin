package com.bixlabs.bkotlin

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Let's you create or reuse bundles in a simple way and with operators. For example:
 *
 * ```
 * val bundle = bundle {
 *       "key".put("value")
 *
 *       // String
 *       put("key", "value")
 *
 *       // Int
 *       put("key", 1)
 *
 *       // Long
 *       put("key", 1L)
 *
 *       // Float
 *       put("key", 1.5131F)
 *
 *       // parcelable
 *       put("key", Parcelable) // Some parcelable object
 *
 *       // parcelable array
 *       put("key", Array<Parcelable>()) // Some parcelable Array
 *
 *       // parcelable arrayList
 *       put("key", ArrayList<Parcelable>()) // Some parcelable ArrayList
 *
 *       // put operators
 *       "key" += 5
 *       this += "keyPair" to 6L
 *
 *       // remove
 *       remove("keyToRemove")
 *
 *       this - ""
 *
 *       // Get a key in a bundle
 *       contains("key")
 *
 *       "key" in this
 * }
 *
 * // Reuse a bundle
 * val bundle = bundle(originalBundle) {
 *       put("key", "value")
 * }
 *
 * ```
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class Bundlify(ofBundle: Bundle? = null) {
    var bundle = ofBundle ?: Bundle()

    //region get
    fun getAll() = bundle

    fun String.getStringArray(): Array<out String> =
            bundle.getStringArray(this)

    fun String.getStringArrayList(): ArrayList<out String> =
            bundle.getStringArrayList(this)

    fun String.getInt() = bundle.getInt(this)

    fun String.getLong() = bundle.getLong(this)

    fun String.getFloat() = bundle.getFloat(this)

    fun String.getBoolean()  = bundle.getBoolean(this)

    fun String.getString(): String = bundle.getString(this)

    fun <T: Parcelable> String.getParcelable(): T = bundle.getParcelable(this)

    @Suppress("UNCHECKED_CAST")
    fun <T: Parcelable> String.getParcelableArray() = bundle.getParcelableArray(this) as Array<T>

    fun <T: Parcelable> String.getParcelableArrayList(): ArrayList<T> =
            bundle.getParcelableArrayList<T>(this)
    //endregion get

    //region contains
    operator fun contains(key: String) = bundle.containsKey(key)
    //endregion contains

    //region put
    fun put(key: String, value: String): Bundlify {
        bundle.putString(key, value)
        return this
    }

    fun put(key: String, value: Int): Bundlify {
        bundle.putInt(key, value)
        return this
    }

    fun put(key: String, value: Long): Bundlify {
        bundle.putLong(key, value)
        return this
    }

    fun put(key: String, value: Boolean): Bundlify {
        bundle.putBoolean(key, value)
        return this
    }

    fun put(key: String, value: Float): Bundlify {
        bundle.putFloat(key, value)
        return this
    }
    fun put(key: String, value: Parcelable): Bundlify {
        bundle.putParcelable(key, value)
        return this
    }
    fun put(key: String, value: Array<Parcelable>): Bundlify {
        bundle.putParcelableArray(key, value)
        return this
    }
    fun put(key: String, value: ArrayList<Parcelable>): Bundlify {
        bundle.putParcelableArrayList(key, value)
        return this
    }
    fun put(key: String, value: Serializable): Bundlify {
        bundle.putSerializable(key, value)
        return this
    }


    @Suppress("UNCHECKED_CAST")
    infix fun String.put(value: Any) {
        when(value) {
            is String ->
                put(this, value)
            is Int ->
                put(this, value)
            is Long ->
                put(this, value)
            is Boolean ->
                put(this, value)
            is Float ->
                put(this, value)
            is Parcelable ->
                put(this, value)
            is Array<*> ->
                put(this, value as Array<Parcelable>)
            is ArrayList<*> ->
                put(this, value as ArrayList<Parcelable>)
            is Serializable -> {
                put(this, value)
            }
        }
    }

    operator fun String.plusAssign(value: Any) = this.put(value)

    operator fun plusAssign(keyValuePair: Pair<String, Any>) =
            keyValuePair.first.put(keyValuePair.second)

    operator fun plus(keyValuePair: Pair<String, Any>) =
            keyValuePair.first.put(keyValuePair.second)

    //endregion put

    //region remove
    fun remove(key: String): Bundlify {
        bundle.remove(key)
        return this
    }

    operator fun minus(key: String) = remove(key)
    //endregion remove
}

/**
 * Created a new instance of [Bundlify]
 */
inline fun bundle(bundle: Bundlify.() -> Unit) = Bundlify().apply(bundle).bundle

/**
 * Creates a new instance of [Bundlify] reusing an already existent [Bundle]
 *
 * @param ofBundle Original [Bundle] to reuse
 */
inline fun bundle(ofBundle: Bundle, bundle: Bundlify.() -> Unit) = Bundlify(ofBundle).apply(bundle).bundle