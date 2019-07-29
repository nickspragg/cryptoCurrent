package com.nickspragg.cryptocurrent.logging

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * Extension of Timber.Tree() to support logging of logs with priority INFO or higher
 * to be logged remotely into Fabric Crashlytics.
 *
 * https://github.com/JakeWharton/timber
 *
 * Copyright 2013 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
class ErrorReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, error: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        Crashlytics.log(priority, tag, message)
        error?.let { Crashlytics.logException(it) }
    }
}