package com.bixlabs.bkotlin

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat

/**
 * Helper method to ease displaying system notifications.
 *
 * Example:
 * ```
 * val notification = Notification.build(context, "myChannel") {
 *   setContentTitle("Hello")
 *   setSubText("World")
 * }
 * ```
 */
inline fun Notification.build(context: Context, channelId: String, func: NotificationCompat.Builder.() -> Unit): Notification {
    val builder = NotificationCompat.Builder(context, channelId)
    builder.func()
    return builder.build()
}
