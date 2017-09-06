package com.bixlabs.bkotlin

import android.content.SharedPreferences

/**
 * Helper method to ease SharedPreferences transactions. `apply` will be automatically invoked for you.
 *
 * Example:
 * ```
 * val prefs = PreferenceManager.getDefaultSharedPreferences(this)
 *
 * prefs.edit {
 *    putString("first_name", "Matias")
 *    putString("last_name", "Radzinski")
 *    putBoolean("Bixlabs", true)
 *    remove("age")
 * }
 *```
 */
inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}