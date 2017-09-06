package com.bixlabs.bkotlin

import android.util.Log

fun Log.v(message: String) = Log.v(TAG, message)
fun Log.d(message: String) = Log.d(TAG, message)
fun Log.i(message: String) = Log.i(TAG, message)
fun Log.w(message: String) = Log.w(TAG, message)
fun Log.e(message: String) = Log.e(TAG, message)
fun Log.wtf(message: String) = Log.wtf(TAG, message)

fun Log.v(message: String, throwable: Throwable) = Log.v(TAG, message, throwable)
fun Log.d(message: String, throwable: Throwable) = Log.d(TAG, message, throwable)
fun Log.i(message: String, throwable: Throwable) = Log.i(TAG, message, throwable)
fun Log.w(message: String, throwable: Throwable) = Log.w(TAG, message, throwable)
fun Log.e(message: String, throwable: Throwable) = Log.e(TAG, message, throwable)
fun Log.wtf(message: String, throwable: Throwable) = Log.wtf(TAG, message, throwable)
