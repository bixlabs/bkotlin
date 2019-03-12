package com.bixlabs.bkotlin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager

/**
 * Hide the soft keyboard
 */
fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * Hide the soft keyboard
 */
fun androidx.fragment.app.Fragment.hideKeyboard() {
    val view = this.activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * Hide the soft keyboard
 */
fun Dialog.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
