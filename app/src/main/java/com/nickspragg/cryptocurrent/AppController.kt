package com.nickspragg.cryptocurrent

import android.app.Application
import com.nickspragg.core.di.CoreComponent
import com.nickspragg.core.di.CoreComponentProvider
import com.nickspragg.core.di.DaggerCoreComponent
import com.nickspragg.cryptocurrent.logging.ErrorReportingTree
import timber.log.Timber

class AppController : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        //Prepare Timber logging support.
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree(), ErrorReportingTree())
        } else {
            Timber.plant(ErrorReportingTree())
        }
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent.builder().build()
        }
        return coreComponent
    }
}