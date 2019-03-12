package com.bixlabs.bkotlin

import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * Creates a new [Canvas] to draw on this bitmap and executes the specified
 * [block] on the newly created canvas. Example:
 *
 * ```
 * return Bitmap.createBitmap(…).applyCanvas {
 *    drawLine(…)
 *    translate(…)
 *    drawRect(…)
 * }
 * ```
 */
inline fun Bitmap.applyCanvas(block: Canvas.() -> Unit): Bitmap {
    val c = Canvas(this)
    c.block()
    return this
}