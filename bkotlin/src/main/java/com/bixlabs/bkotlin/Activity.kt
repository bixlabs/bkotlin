package com.bixlabs.bkotlin

import android.app.Activity
import android.support.annotation.UiThread
import android.view.WindowManager


/**
 * Makes the activity enter fullscreen mode.
 *
 * Window flag: hide all screen decorations (such as the status bar) while
 * this window is displayed. This allows the window to use the entire
 * display space for itself -- the status bar will be hidden when
 * an app window with this flag set is on the top layer. A fullscreen window
 * will ignore a value of [android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE]
 * for the window's [android.view.WindowManager.LayoutParams.softInputMode] field;
 * the window will stay fullscreen and will not resize.
 */
@UiThread
fun Activity.enterFullScreenMode() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

/**
 * Makes the Activity exit fullscreen mode.
 *
 * Window flag: override [android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN]
 * and force the screen decorations (such as the status bar) to be shown.
 */
@UiThread
fun Activity.exitFullScreenMode() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
}