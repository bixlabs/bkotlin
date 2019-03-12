@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package com.bixlabs.bkotlin

import android.net.Uri
import java.io.File

/**
 * Creates a Uri from the given encoded URI string.
 *
 * @see Uri.parse
 */
inline fun String.toUri(): Uri = Uri.parse(this)

/**
 * Creates a Uri from the given file.
 *
 * @see Uri.fromFile
 */
inline fun File.toUri(): Uri = Uri.fromFile(this)

/** Creates a [File] from the given [Uri]. */
fun Uri.toFile(): File {
    require(scheme == "file") { "Uri lacks 'file' scheme: $this" }
    return File(path)
}