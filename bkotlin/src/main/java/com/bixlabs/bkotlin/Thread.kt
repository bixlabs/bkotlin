package com.bixlabs.bkotlin

import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 * Runs the specified action on the UI thread. This method can be invoked from anywhere on the application
 */
fun runOnUiThread(action: () -> Unit) = Handler(Looper.getMainLooper()).post(Runnable(action))

/**
 * Runs the specified action on a background thread with delay.
 * @param[delayInMillis] delay in ms
 */
fun runDelayed(delayInMillis: Long, callback: () -> Unit) = Handler()
        .postDelayed(Runnable(callback), delayInMillis)

/**
 * Runs the specified action on the UI thread with delay.
 * @param[delayInMillis] The delay in milliseconds
 */
fun Context.runDelayedOnUiThread(delayInMillis: Long, callback: () -> Unit) = Handler(this.mainLooper)
        .postDelayed({ callback() }, delayInMillis)