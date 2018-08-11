package com.bixlabs.bkotlin

import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 * Runs the specified action on the UI thread. This method can be invoked from anywhere on the application
 */
inline fun runOnUiThread(crossinline action: () -> Unit) = Handler(Looper.getMainLooper())
        .post { action.invoke() }

/**
 * Runs the specified action on a background thread with delay.
 * @param[delayInMillis] delay in ms
 */
inline fun runDelayed(delayInMillis: Long, crossinline callback: () -> Unit) = Handler()
        .postDelayed({ callback.invoke() }, delayInMillis)

/**
 * Runs the specified action on the UI thread with delay.
 * @param[delayInMillis] The delay in milliseconds
 * @param[callback] The block that will be executed
 */
inline fun Context.runDelayedOnUiThread(delayInMillis: Long, crossinline callback: () -> Unit) = Handler(this.mainLooper)
        .postDelayed({ callback.invoke() }, delayInMillis)

/**
 * Safe version of [runDelayedOnUiThread] where [callback] is surrounded by [attempt] to
 * prevent it from crashing upon execution of the same.
 * @param[delayInMillis] The delay in milliseconds
 * @param[callback] The block that will be executed
 */
inline fun Context.safeRunDelayedOnUiThread(delayInMillis: Long, crossinline callback: () -> Unit) = Handler(this.mainLooper)
        .postDelayed({ attempt { callback.invoke() } }, delayInMillis)