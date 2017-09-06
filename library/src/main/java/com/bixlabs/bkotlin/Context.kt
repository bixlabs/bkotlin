package com.bixlabs.bkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

/**
 * Display a Toast Message
 *
 * @param[message] to display
 * @param[length] Length of display time of Toast, Default is Toast.LENGTH_LONG
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()

/**
 * Display a Toast Message
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
 * get Height of status bar
 * @return height of status bar
 */
fun Context.getStatusBarHeight(): Int {
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    return this.resources.getDimensionPixelSize(resourceId)
}