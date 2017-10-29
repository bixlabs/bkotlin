package com.bixlabs.bkotlin

import android.os.Build

/**
 * Execute [f] only if the current Android SDK version is [version] or newer. Optionally, execute [else] if the
 * current Android SDK version is lower than the provided one.
 */
fun doStartingFromSdk(version: Int, f: () -> Unit, `else`: () -> Unit = {}) {
    if (Build.VERSION.SDK_INT >= version) {
        f()
    } else {
        `else`()
    }
}

/**
 * Execute [f] only if the current Android SDK version is [version] or newer.
 */
fun doStartingFromSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT >= version) {
        f()
    } else {
        /* no-op */
    }
}

/**
 * Execute [f] only if the current Android SDK version is [version] Optionally, execute [else] if the
 * current Android SDK version doesn't match the provided one.
 */
fun doIfSdk(version: Int, f: () -> Unit, `else`: () -> Unit = {}) {
    if (Build.VERSION.SDK_INT == version) {
        f()
    } else {
        `else`()
    }
}

/**
 * Execute [f] only if the current Android SDK version is [version]
 */
fun doIfSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT == version) {
        f()
    } else {
        /* no-op */
    }
}