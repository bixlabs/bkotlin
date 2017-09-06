package com.bixlabs.bkotlin

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Parse a date from String
 *
 * @param[format] optional, Date format. defaults to yyyy-MM-dd HH:mm:ss (ex: 2017-06-02 19:20:00)
 * @return Date object, Nullable
 */
fun Date.parse(date: String, format: String? = "yyyy-MM-dd HH:mm:ss"): Date? = try {
    SimpleDateFormat(format, Locale.getDefault()).parse(date)
} catch (e: Exception) {
    null
}

/**
 * Formats a date using SimpleDateFormat to the received string format
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2017-06-02 19:20:00)
 * @return Formatted Date as String
 */
fun Date.toFormattedString(format: String? = "yyyy-MM-dd HH:mm:ss"): String =
        SimpleDateFormat(format, Locale.getDefault()).format(this)

/**
 * Formats a date to a human redable duration string such as "1 min ago"
 */
fun Date.toStringDuration(): String = this.time.toTimeAgo()

/**
 * Given a date in millis since EPOCH, formats it to a human redable duration string such as "1 min ago"
 */
fun Long.toTimeAgo(): String {
    val date = Date(this)
    val cal = Calendar.getInstance()
    var time: String = String.EMPTY()
    val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US)
    val crdate = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(date)
    val currentTime = dateFormat.format(cal.time)

    val createdAt: Date?
    val now: Date?

    try {
        createdAt = dateFormat.parse(crdate)
        now = dateFormat.parse(currentTime)
    } catch (e: java.text.ParseException) {
        e.printStackTrace()
        return time
    }

    if (now != null && createdAt != null) {
        val diff = now.time - createdAt.time
        val diffMinutes = diff / (60 * 1000) % 60
        val diffHours = diff / (60 * 60 * 1000) % 24
        val diffDays = diff / (24 * 60 * 60 * 1000)
        val diffMonths = diff / TimeUnit.DAYS.toMillis(30)
        val diffYears = diff / TimeUnit.DAYS.toMillis(365)

        when {
            diffDays > 0 -> time = if (diffDays == 1L) {
                "Yesterday"
            } else {
                if (diffDays <= 365L) {
                    if (diffDays >= 30) {
                        "$diffMonths ${if (diffMonths > 1) "months" else "month"} ago"
                    } else {
                        "$diffDays ${if (diffDays > 1) "days" else "day"} ago"
                    }
                } else {
                    "$diffYears ${if (diffYears > 1) "years" else "year" } ago"
                }
            }

            diffHours > 0 -> {
                time = if (diffHours == 1L) {
                    "$diffHours hr ago"
                } else {
                    "$diffHours hrs ago"
                }
            }

            else -> {
                time = if (diffMinutes > 0) {
                    if (diffMinutes == 1L) {
                        "$diffMinutes min ago"
                    } else {
                        "$diffMinutes mins ago"
                    }
                } else {
                    "Just now"
                }
            }
        }
    }

    return time
}