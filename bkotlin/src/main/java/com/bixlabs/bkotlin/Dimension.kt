package com.bixlabs.bkotlin

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.DisplayMetrics

/**
 * Converts dp to px
 *
 * @param[value] to convert
 * @return calculated dp
 */
fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

/**
 * Converts dp to px
 *
 * @param[value] to convert
 * @return calculated dp
 * @since 1.0.1
 */
fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()

/**
 * Converts sp to px
 *
 * @param[value] to convert
 * @return calculated sp
 */
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

/**
 * Converts sp to px
 *
 * @param[value] to convert
 * @return calculated sp
 */
fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

/**
 * Converts px to dp
 *
 * @param[px] to convert
 * @return calculated dp
 */
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density

/**
 * Converts px to sp
 *
 * @param[px] to convert
 * @return calculated sp
 */
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity


/**
 * Get the screen dimensions for the device in px
 *
 * @param[px] to convert
 * @return calculated sp
 */
fun Activity.getDisplayMetrics(): DisplayMetrics {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)

    return metrics
}

/**
 * Get the screen dimensions for the device in px
 *
 * @param[px] to convert
 * @return calculated sp
 */
fun Fragment.getDisplayMetrics(): DisplayMetrics {
    val metrics = DisplayMetrics()
    activity?.windowManager?.defaultDisplay?.getMetrics(metrics)

    return metrics
}