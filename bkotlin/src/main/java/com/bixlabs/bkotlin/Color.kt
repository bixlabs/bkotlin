package com.bixlabs.bkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorRes


/**
 * Return the color with 0xFF opacity.
 * E.g., `0xabcdef` will be translated to `0xFFabcdef`.
 */
val Int.opaque: Int
    get() = this or 0xff000000.toInt()

/**
 * Return the color with the given alpha value.
 * Examples:
 * ```
 *   0xabcdef.withAlpha(0xCF) == 0xCFabcdef
 *   0xFFabcdef.withAlpha(0xCF) == 0xCFabcdef
 * ```
 *
 * @param alpha the alpha channel value: [0x0..0xFF].
 * @return the color with the given alpha value applied.
 */
fun Int.withAlpha(alpha: Int): Int {
    require(alpha in 0..0xFF)
    return this and 0x00FFFFFF or (alpha shl 24)
}

/**
 * Returns a themed color integer associated with a particular resource ID.
 * If the resource holds a complex ColorStateList, then the default color from the set is returned.
 * @param [color] The color resource
 * @param [theme] Resources.Theme: The theme used to style the color attributes, may be null. The same
 *                will not be used on Android versions lower than Marshmallow.
 * @return A single color value in the form 0xAARRGGBB
 */
@SuppressLint("NewApi")
fun Context.getColorCompat(@ColorRes color: Int, theme: Resources.Theme? = null): Int {
    var c: Int = 0

    doStartingFromSdk(Build.VERSION_CODES.M, {
        c = this.resources.getColor(color, theme)
    }, {
        c = this.resources.getColor(color)
    })

    return c
}

/**
 * Get a brighter (luma) version of this [Color]
 */
fun Int.getLuma(): Double =
        0.299 * Color.red(this) + 0.587 * Color.green(this) + 0.114 * Color.blue(this)

/**
 * Get a darker version of this [Color]
 */
fun Int.getDarker(alpha: Float): Int =
        Color.rgb((Color.red(this) * alpha).toInt(), (Color.green(this) * alpha).toInt(),
            (Color.blue(this) * alpha).toInt())

/**
 * Convert this color to a HEX [String]
 */
fun Int.toHexString(): String = String.format("#%06X", 0xFFFFFF and this)