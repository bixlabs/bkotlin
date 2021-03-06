package com.bixlabs.bkotlin

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Accepts 3 text watcher methods with a default empty implementation.
 * This allows for a cleaner implementation of a `TextWatcher` with only the needed
 * parts of if being implemented.
 *
 * @return The `TextWatcher` being added to EditText
 */
fun EditText.addTextWatcher(afterTextChanged: (s: Editable?) -> Unit = { _ -> },
                            beforeTextChanged: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
                            onTextChanged: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }): TextWatcher {

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(s, start, before, count)
        }
    }

    addTextChangedListener(textWatcher)
    return textWatcher
}

/**
 * Get this EditText text as a trimmed [String].
 */
fun EditText.textAsTrimmedString(): String = this.text.toString().trim()