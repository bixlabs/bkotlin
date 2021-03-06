package com.bixlabs.bkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * Reboot the application
 *
 * @param[restartIntent] optional, desired activity to show after the reboot
 */
fun Context.reboot(restartIntent: Intent = this.packageManager.getLaunchIntentForPackage(this.packageName)) {
    restartIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    if (this is Activity) {
        this.startActivity(restartIntent)
        finishAffinity(this)
    } else {
        restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(restartIntent)
    }
}

/**
 * Get the application version code
 */
fun Context.getVersionCode(): Int = packageManager.getPackageInfo(packageName, 0).versionCode

/**
 * Get the application version name
 */
fun Context.getVersionName(): String = packageManager.getPackageInfo(packageName, 0).versionName


/* ********************************************
 *               Private methods              *
 ******************************************** */

private fun finishAffinity(activity: Activity) {
    activity.setResult(Activity.RESULT_CANCELED)
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> activity.finishAffinity()
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN -> activity.runOnUiThread { activity.finishAffinity() }
        else -> ActivityCompat.finishAffinity(activity)
    }
}