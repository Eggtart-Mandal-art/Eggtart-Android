package com.baker.eggtart.common.util

import android.util.Log
import com.baker.eggtart.common.util.BuildConfig

/**
 *  Created by wonjin on 2024/04/08
 **/

object Logger {
    private val TAG = "Eggtart"

    fun d(msg: String) = d(TAG, msg)

    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun e(msg: String) = e(TAG, msg)
    fun e(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun i(msg: String) = i(TAG, msg)
    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg)
        }
    }

    fun v(msg: String) = v(TAG, msg)
    fun v(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg)
        }
    }

    fun w(msg: String) = w(TAG, msg)
    fun w(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg)
        }
    }
}