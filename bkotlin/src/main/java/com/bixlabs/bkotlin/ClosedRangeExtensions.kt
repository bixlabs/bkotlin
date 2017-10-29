package com.bixlabs.bkotlin

import android.annotation.TargetApi
import android.os.Build
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Get a random number given a [ClosedRange] of [Int]'s
 */
fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

/**
 * Get a random number given a [ClosedRange] of [Long]'s
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun ClosedRange<Long>.random() = ThreadLocalRandom.current().nextLong(start, endInclusive)