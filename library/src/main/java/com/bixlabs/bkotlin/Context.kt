package com.bixlabs.bkotlin

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Hides all the views passed in the arguments
 */
fun Context.hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

/**
 * Shows all the views passed in the arguments
 */
fun Context.showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }

/**
 * Display Toast Message
 *
 * @param[message] to display
 * @param[length] Length of display time of Toast, Default is Toast.LENGTH_LONG
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()

/**
 * Display Toast Message
 *
 * @param[message] to display
 * @param[length] Length of display time of Toast, Default is Toast.LENGTH_LONG
 */
fun Context.toast(message: Int, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()

/**
 * Shortcut for LayoutInflater.from(this)
 */
fun Context.inflate(layoutResource: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) {
    LayoutInflater.from(this).inflate(layoutResource, parent, attachToRoot)
}

/**
 * Get the application version code
 */
fun Context.getVersionCode(): Int = packageManager.getPackageInfo(packageName, 0).versionCode

/**
 * Get the application version name
 */
fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, 0).versionName

/**
 * Runs the specified action on the UI thread with delay.
 * @param[delayInMillis] The delay in milliseconds
 */
fun Context.runOnUiDelayed(delayInMillis: Long, callback: () -> Unit) {
    val handler = Handler(this.mainLooper)
    handler.postDelayed({ callback() }, delayInMillis)
}

/**
 * get Height of status bar
 *
 * @return height of status bar
 */
fun Context.getStatusBarHeight(): Int {
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    return this.resources.getDimensionPixelSize(resourceId)
}