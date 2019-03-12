package com.bixlabs.bkotlin

import android.util.AtomicFile
import androidx.annotation.RequiresApi
import java.io.FileOutputStream
import java.nio.charset.Charset

/**
 * Perform the write operations inside [block] on this file. If [block] throws an exception the
 * write will be failed. Otherwise the write will be applied atomically to the file.
 */
@RequiresApi(17)
inline fun AtomicFile.tryWrite(block: (out: FileOutputStream) -> Unit) {
    val stream = startWrite()
    var success = false
    try {
        block(stream)
        success = true
    } finally {
        if (success) {
            finishWrite(stream)
        } else {
            failWrite(stream)
        }
    }
}
/**
 * Sets the content of this file as an [array] of bytes.
 */
@RequiresApi(17)
fun AtomicFile.writeBytes(array: ByteArray) {
    tryWrite {
        it.write(array)
    }
}
/**
 * Sets the content of this file as [text] encoded using UTF-8 or specified [charset].
 * If this file exists, it becomes overwritten.
 */
@RequiresApi(17)
fun AtomicFile.writeText(text: String, charset: Charset = Charsets.UTF_8) {
    writeBytes(text.toByteArray(charset))
}
/**
 * Gets the entire content of this file as a byte array.
 *
 * This method is not recommended on huge files. It has an internal limitation of 2 GB file size.
 */
@RequiresApi(17)
inline fun AtomicFile.readBytes(): ByteArray = readFully()
/**
 * Gets the entire content of this file as a String using UTF-8 or specified [charset].
 *
 * This method is not recommended on huge files. It has an internal limitation of 2 GB file size.
 */
@RequiresApi(17)
fun AtomicFile.readText(charset: Charset = Charsets.UTF_8): String {
    return readFully().toString(charset)
}