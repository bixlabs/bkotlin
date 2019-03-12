package com.bixlabs.bkotlin

import android.content.Context
import androidx.annotation.StringRes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 * @param message the message text.
 */
fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 * @param message the message text resource.
 */
fun Context.shortToast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 * @param message the message text.
 */
fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 * @param message the message text resource.
 */
fun Context.longToast(@StringRes message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

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

