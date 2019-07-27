package com.nickspragg.cryptocurrents.logging

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

class ErrorReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, error: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        Crashlytics.log(priority, tag, message)

        error?.let { Crashlytics.logException(it) }
    }
}