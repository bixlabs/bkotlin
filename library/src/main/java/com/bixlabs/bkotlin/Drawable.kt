package com.bixlabs.bkotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * Converts a Bitmap to a Drawable
 * @return The Drawable Object
 */
fun Context.bitmapToDrawable(bitmap: Bitmap?): Drawable? = BitmapDrawable(this.resources, bitmap)

/**
 * Converts this Bitmap to a Drawable
 * @return The Drawable Object
 */
fun Bitmap.toDrawable(context: Context): Drawable? = BitmapDrawable(context.resources, this)