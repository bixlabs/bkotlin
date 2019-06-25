@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.bixlabs.bkotlin

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo

@DslMarker
annotation class OnlyDsl

/**
 * An easy way of running tasks a specific amount of times throughout the lifespan of an app,
 * either between versions of itself or regardless of the same.
 */
object Only {
    private lateinit var preference: SharedPreferences
    private lateinit var buildVersion: String
    private var isDebuggable = 0
    private var doOnDebugMode = false

    /** initialize [Only] */
    fun init(context: Context) {
        val info = context.packageManager.getPackageInfo(context.packageName, 0)
        init(context, info.versionName)
    }

    /** initialize [Only], providing a specific version for the tasks. */
    fun init(context: Context, version: String) {
        this.preference = context.applicationContext.getSharedPreferences("Only", Context.MODE_PRIVATE)
        this.isDebuggable = context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        this.buildVersion = version
    }

    /** Sets if [Only] should be in debug mode. */
    fun setDebugMode(debug: Boolean): Only {
        this.doOnDebugMode = debug
        return this@Only
    }

    /** Checks if [Only] is in debug mode. */
    fun isDebugModeEnabled(): Boolean = doOnDebugMode && isDebuggable != 0

    /** Gets the amount of times a specific task has been run. */
    fun getTaskTimes(taskName: String): Int = this.preference.getInt(taskName, 0)

    /** Sets the amount of times a specific task has been run. */
    fun setTaskTimes(taskName: String, time: Int) = this.preference.edit().putInt(taskName, time).apply()

    /** Gets the version of a specific task if any */
    fun getTaskVersion(taskName: String): String? = preference.getString(getTaskVersionName(taskName), buildVersion)

    /** Clear the amount of times a specific task has been run. */
    fun clearTask(taskName: String) = this.preference.edit().remove(taskName).apply()

    /** Clear the amount of times for all tasks. */
    fun clearAllTasks() = this.preference.edit().clear().apply()


    /* ********************************************
     *               Private methods              *
     ******************************************** */

    private inline fun onDo(name: String, times: Int, crossinline onDo: () -> Unit, crossinline onDone: () -> Unit, version: String = ""): Only {
        checkTaskVersion(name, version)

        // run only onDo block when debug mode.
        if (isDebugModeEnabled()) {
            onDo()
            return this@Only
        }

        val persistVersion = getTaskTimes(name)

        if (persistVersion < times) {
            setTaskTimes(name, persistVersion + 1)
            onDo()
        } else {
            onDone()
        }

        return this@Only
    }

    private fun checkTaskVersion(taskName: String, version: String): Boolean {
        val theVersion = if (version.isEmpty()) buildVersion else version

        if (getTaskVersion(taskName).equals(theVersion)) return true

        setTaskTimes(taskName, 0)
        setTaskVersion(taskName, theVersion)

        return false
    }

    private fun runByBuilder(builder: Builder) =
            onDo(builder.name, builder.times, builder.onDo, builder.onPreviouslyDone, builder.version)

    private fun setTaskVersion(name: String, version: String) =
            this.preference.edit().putString(getTaskVersionName(name), version).apply()

    private fun getTaskVersionName(name: String): String = "${name}_version"


    @OnlyDsl
    class Builder internal constructor(val name: String, val times: Int = 1) {
        var onDo: () -> Unit = { }
        var onPreviouslyDone: () -> Unit = { }
        var version: String = ""

        fun onDo(onDo: () -> Unit): Builder = apply { this.onDo = onDo }
        fun onPreviouslyDone(onDone: () -> Unit): Builder = apply { this.onPreviouslyDone = onDone }
        fun taskVersion(version: String): Builder = apply { this.version = version }

        fun run() = runByBuilder(this@Builder)
    }
}

/** Run [Only] by [Only.Builder] using kotlin DSL. */
@OnlyDsl
fun only(taskName: String, times: Int, block: Only.Builder.() -> Unit): Unit = Only.Builder(taskName, times)
        .apply(block)
        .run()
        .toUnit()