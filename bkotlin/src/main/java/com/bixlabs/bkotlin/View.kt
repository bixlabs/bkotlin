package com.bixlabs.bkotlin

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Handler
import android.support.annotation.Px
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup

class ShowHideIfBuilder (private val view: View, private val condition: Boolean) {
    fun elseShow(): ShowHideIfBuilder {
        if (!condition) view.show()
        return this
    }
    
    fun elseHide(): ShowHideIfBuilder {
        if (!condition) view.hide()
        return this
    }
    
    fun elseGone(): ShowHideIfBuilder {
        if (!condition) view.gone()
        return this
    }
    
    inline fun andThen(block: () -> Unit): ShowHideIfBuilder {
        block.invoke()
        return this
    }
}

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
    val handler = Handler(this.activity?.mainLooper)
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
    val handler = Handler(this.activity?.mainLooper)
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
 * Fades in the View.
 * @param duration The duration of the animation
 * @param showIfInvisible If this view is invisible and this parameter is set to true the view will be first set to visible with an alpha of 0.0.
 * @param listener An optional animator listener in case you want to perform operations during any part of the lifecycle of the animation
 */
fun View.fadeIn(duration: Long = 400, showIfInvisible: Boolean = true, listener: Animator.AnimatorListener? = null) {
    if (showIfInvisible && (this.isHidden() || this.isGone())) {
        this.alpha = 0.0F
        this.show()
    }

    val animation = animate().alpha(1.0f).setDuration(duration)
    if (listener != null) animation.setListener(listener)

    animation.start()
}

/**
 * Fades out the View.
 * @param duration The duration of the animation
 * @param showIfInvisible If this view is invisible and this parameter is set to true the view will be first set to visible with an alpha of 1.0.
 * @param listener An optional animator listener in case you want to perform operations during any part of the lifecycle of the animation
 */
fun View.fadeOut(duration: Long = 400, showIfInvisible: Boolean = true, listener: Animator.AnimatorListener? = null) {
    if (showIfInvisible && (this.isHidden() || this.isGone())) {
        this.alpha = 1.0F
        this.show()
    }

    val animation = animate().alpha(0.0f).setDuration(duration)
    if (listener != null) animation.setListener(listener)

    animation.start()
}

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

/**
 * Apply [f] to this [View] and to all of its children recursively.
 *
 * @return the receiver.
 */
fun <T : View> T.applyRecursively(f: (View) -> Unit): T {
    applyRecursively(this, f)
    return this
}

/**
 * Locates this view on screen if the same is [View.VISIBLE]
 * @return The position of this view on screen if the same is [View.VISIBLE]
 */
fun View.locateInScreen(): Rect? {
    val loc_int = IntArray(2)

    val attempt = attempt {
        this@locateInScreen.getLocationOnScreen(loc_int)
    }

    if (attempt.isError) return null

    return Rect().apply {
        left = loc_int[0]
        top = loc_int[1]
        right = left + this@locateInScreen.width
        bottom = top + this@locateInScreen.height
    }
}

/**
 * Show this view if the received condition becomes true
 * @return a [ShowHideIfBuilder] instance allowing composition through.
 */
fun View.showIf(condition: Boolean): ShowHideIfBuilder {
    if (condition) this.show()
    return ShowHideIfBuilder(this, condition)
}

/**
 * Hide this view if the received condition becomes true
 * @return a [ShowHideIfBuilder] instance allowing composition through.
 */
fun View.hideIf(condition: Boolean): ShowHideIfBuilder {
    if (condition) this.hide()
    return ShowHideIfBuilder(this, condition)
}

/**
 * Gone this view if the received condition becomes true
 * @return a [ShowHideIfBuilder] instance allowing composition through.
 */
fun View.goneIf(condition: Boolean): ShowHideIfBuilder {
    if (condition) this.gone()
    return ShowHideIfBuilder(this, condition)
}

/**
 * Resize this view to the provided height, keeping its width intact.
 * @param value The desired final height for this view in pixels
 */
fun View.resizeHeight(@Px value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

/**
 * Resize this view to the provided width, keeping its height intact.
 * @param value The desired final width for this view in pixels
 */
fun View.resizeWidth(@Px value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}

/**
 * Resize this view.
 * @param width The desired final width for this view in pixels
 * @param height The desired final height for this view in pixels
 */
fun View.resize(@Px width: Int, @Px height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = width
        lp.height = height
        layoutParams = lp
    }
}

/**
 * Sets the relative top padding for this view in pixels.
 * @param value The top padding in pixels
 */
fun View.setPaddingTop(@Px value: Int) = setPaddingRelative(paddingStart, value, paddingEnd, paddingBottom)

/**
 * Sets the relative bottom padding for this view in pixels.
 * @param value The bottom padding in pixels
 */
fun View.setPaddingBottom(@Px value: Int) = setPaddingRelative(paddingStart, paddingTop, paddingEnd, value)

/**
 * Sets the relative start padding for this view in pixels.
 * @param value The start padding in pixels
 */
fun View.setPaddingStart(@Px value: Int) = setPaddingRelative(value, paddingTop, paddingEnd, paddingBottom)

/**
 * Sets the relative end padding for this view in pixels.
 * @param value The end padding in pixels
 */
fun View.setPaddingEnd(@Px value: Int) = setPaddingRelative(paddingStart, paddingTop, value, paddingBottom)

/**
 * Return a [Bitmap] representation of this [View].
 *
 * The resulting bitmap will be the same width and height as this view's current layout
 * dimensions. This does not take into account any transformations such as scale or translation.
 *
 * Note, this will use the software rendering pipeline to draw the view to the bitmap. This may
 * result with different drawing to what is rendered on a hardware accelerated canvas (such as
 * the device screen).
 *
 * If this view has not been laid out this method will throw a [IllegalStateException].
 *
 * @param config Bitmap config of the desired bitmap. Defaults to [Bitmap.Config.ARGB_8888].
 */
fun View.drawToBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    if (!ViewCompat.isLaidOut(this)) {
        throw IllegalStateException("View needs to be laid out before calling drawToBitmap()")
    }
    return Bitmap.createBitmap(width, height, config).applyCanvas {
        translate(-scrollX.toFloat(), -scrollY.toFloat())
        draw(this)
    }
}


/* ********************************************
 *               Private methods              *
 ******************************************** */

fun applyRecursively(v: View, style: (View) -> Unit) {
    style(v)
    if (v is ViewGroup) {
        val maxIndex = v.childCount - 1
        for (i in 0 .. maxIndex) {
            v.getChildAt(i)?.let { applyRecursively(it, style) }
        }
    }
}