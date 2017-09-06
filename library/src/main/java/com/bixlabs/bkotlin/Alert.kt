package com.bixlabs.bkotlin

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.text.TextUtils

/**
 * Display an AlertDialog
 *
 * @param[title] optional, title
 * @param[message] to display
 * @param[positiveButton] optional, button text
 * @param[cancelable] able to cancel
 * @param[callback] callback of click ok button
 */
fun Context.displayAlertDialog(title: String? = "", message: String, positiveButton: String? = null,
                  cancelable: Boolean = true, callback: DialogInterface.() -> Unit = {}) =
        AlertDialog.Builder(this).apply {
            if (!TextUtils.isEmpty(title))
                setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButton ?: getString(android.R.string.ok), { dialog, _ -> dialog.callback() })
            setCancelable(cancelable)
            show()
        }

/**
 * Display an AlertDialog with confirmation/cancellation options
 *
 * @param[title] optional, title
 * @param[message] to display
 * @param[positiveButton] optional, possitive button text. Defaults to true
 * @param[negativeButton] optional, negative button text. Defaults to false
 * @param[cancelable] optional, cancelable. Defaults to true
 * @param[callback] callback which includes the option selected by the user. True if the user pressed the possitive button,
 *                  false otherwise.
 */
fun Context.displayConfirmDialog(title: String? = "", message: String, positiveButton: String? = null, negativeButton: String? = null,
                    cancelable: Boolean = true, callback: DialogInterface.(result: Boolean) -> Unit) =
        AlertDialog.Builder(this).apply {
            if (!TextUtils.isEmpty(title))
                setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButton ?: getString(android.R.string.ok), { dialog, _ -> dialog.callback(true) })
            setNegativeButton(negativeButton ?: getString(android.R.string.no), { dialog, _ -> dialog.callback(false) })
            setCancelable(cancelable)
            show()
        }

/**
 * Display a ProgressDialog
 *
 * @param[title] optional, title
 * @param[message] message
 * @return DialogInterface which allows the dismissal of the ProgressDialog
 */
fun Context.displayProgressDialog(title: String? = null, message: String): DialogInterface {
    return ProgressDialog(this).apply {
        setProgressStyle(ProgressDialog.STYLE_SPINNER)
        setMessage(message)
        if (title != null)
            setTitle(title)
        show()
    }
}