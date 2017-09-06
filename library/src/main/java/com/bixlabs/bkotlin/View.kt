package com.bixlabs.bkotlin

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator

/**
 * Gives focus to the passed view once the view has been completely inflated
 */
fun Activity.setFocusToView(view: View) {
    val handler = Handler(this.mainLooper)
    handler.post { view.requestFocus() }
}

/**
 * Gives focus to the passed view once the view has been completely inflated
 */
fun Fragment.setFocusToView(view: View) {
    val handler = Handler(this.activity.mainLooper)
    handler.post { view.requestFocus() }
}

/**
 * Gives focus to the passed view once the view has been completely
 * inflated using `view.requestFocusFromTouch`
 */
fun Activity.setTouchFocusToView(view: View) {
    val handler = Handler(this.mainLooper)
    handler.post { view.requestFocusFromTouch() }
}

/**
 * Gives focus to the passed view once the view has been completely
 * inflated using `view.requestFocusFromTouch`
 */
fun Fragment.setTouchFocusToView(view: View) {
    val handler = Handler(this.activity.mainLooper)
    handler.post { view.requestFocusFromTouch() }
}

/**
 * Sets the view's visibility to GONE
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Sets the view's visibility to VISIBLE
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Sets the view's visibility to INVISIBLE
 */
fun View.hide() {
    visibility = View.INVISIBLE
}

/**
 * Toggle's view's visibility. If View is visible, then sets to GONE. Else sets VISIBLE
 */
fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

/**
 * Fades in the View
 */
fun View.fadeIn(duration: Long = 400): ViewPropertyAnimator? = animate()
        .alpha(1.0f)
        .setDuration(duration)


/**
 * Fades out the View
 */
fun View.fadeOut(duration: Long = 400): ViewPropertyAnimator? = animate()
        .alpha(0.0f)
        .setDuration(duration)

/**
 * True if the view is currently visible (View.VISIBLE), false otherwise
 */
fun View.isVisible(): Boolean = visibility == View.VISIBLE

/**
 * True if the view is currently invisible (View.INVISIBLE), false otherwise
 */
fun View.isHidden(): Boolean = visibility == View.INVISIBLE

/**
 * True if the view is currently gone (View.GONE), false otherwise
 */
fun View.isGone(): Boolean = visibility == View.GONE

/**
 * Hides all the views passed as argument(s)
 */
fun Context.hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

/**
 * Shows all the views passed as argument(s)
 */
fun Context.showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }

/**
 * Get all child views from a ViewGroup
 */
val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }