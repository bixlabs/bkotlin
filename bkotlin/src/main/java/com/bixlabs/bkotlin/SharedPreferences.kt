package com.bixlabs.bkotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

@Suppress("unused")
object GlobalSharedPreferences  {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var initialized = false

    /**
     * @param application Global context in order use everywhere
     *      without the need for context every time
     * @param sharedPreferencesName custom name for SharedPreferences
     * @return instance of the GlobalSharedPreferences
     */
    @SuppressLint("CommitPrefEdits")
    fun initialize(application: Application, sharedPreferencesName: String = "DefaultSharedPreferences"):
            GlobalSharedPreferences {
        sharedPreferences = application.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        initialized = true
        return this
    }

    fun getAll(): Map<String, *> = requiredOrThrow(sharedPreferences.all)

    fun getInt(key: String, defaultValue: Int) =
            requiredOrThrow(sharedPreferences.getInt(key, defaultValue))

    fun getLong(key: String, defaultValue: Long) =
            requiredOrThrow(sharedPreferences.getLong(key, defaultValue))

    fun getFloat(key: String, defaultValue: Float) =
            requiredOrThrow(sharedPreferences.getFloat(key, defaultValue))

    fun getBoolean(key: String, defaultValue: Boolean) =
            requiredOrThrow(sharedPreferences.getBoolean(key, defaultValue))

    fun getString(key: String, defaultValue: String) : String =
            requiredOrThrow(sharedPreferences.getString(key, defaultValue))

    fun getStringSet(key: String, defaultValue: Set<String>): MutableSet<String>? =
            requiredOrThrow(sharedPreferences.getStringSet(key, defaultValue))

    infix fun String.forStringSet(defaultValue: Set<String>): MutableSet<String>? =
            requiredOrThrow(sharedPreferences.getStringSet(this, defaultValue))

    infix fun String.forInt(defaultValue: Int) =
            requiredOrThrow(sharedPreferences.getInt(this, defaultValue))

    infix fun String.forLong(defaultValue: Long) =
            requiredOrThrow(sharedPreferences.getLong(this, defaultValue))

    infix fun String.forFloat(defaultValue: Float) =
            requiredOrThrow(sharedPreferences.getFloat(this, defaultValue))

    infix fun String.forBoolean(defaultValue: Boolean) =
            requiredOrThrow(sharedPreferences.getBoolean(this, defaultValue))

    infix fun String.forString(defaultValue: String) : String =
            requiredOrThrow(sharedPreferences.getString(this, defaultValue))

    operator fun contains(key: String) = requiredOrThrow(sharedPreferences.contains(key))

    fun put(key: String, value: String) = also { editor.putString(key, value) }

    fun put(key: String, value: Int) = also { editor.putInt(key, value) }

    fun put(key: String, value: Long) = also { editor.putLong(key, value) }

    fun put(key: String, value: Boolean) = also { editor.putBoolean(key, value) }

    fun put(key: String, value: Float) = also { editor.putFloat(key, value) }

    fun put(key: String, value: Set<String>) = also { editor.putStringSet(key, value) }

    infix fun String.put(value: Any) {
        also {
            when (value) {
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
                is Set<*> -> {
                    put(this, value.map { it.toString() }.toSet())
                }
            }
        }
    }

    fun remove(key: String) = also { editor.remove(key) }

    operator fun String.plusAssign(value: Any) = this.put(value)

    operator fun plusAssign(keyValuePair: Pair<String, Any>) =
            keyValuePair.first.put(keyValuePair.second)

    operator fun plus(keyValuePair: Pair<String, Any>) =
            keyValuePair.first.put(keyValuePair.second)

    operator fun minus(key: String) = remove(key)


    fun commit() = editor.commit()

    fun apply() = editor.apply()

    /**
     * @param returnIfInitialized object to be returned if class is initialized
     * @throws RuntimeException
     */
    @Throws(RuntimeException::class)
    private fun <T> requiredOrThrow(returnIfInitialized: T) = if (initialized) {
        returnIfInitialized
    } else {
        throw RuntimeException("GlobalSharedPreferences need to be initialized before its usage")
    }
}


inline fun prefs(sharedPreferences: GlobalSharedPreferences.() -> Unit) = with(GlobalSharedPreferences) {
    also {
        sharedPreferences()
        apply()
    }
}