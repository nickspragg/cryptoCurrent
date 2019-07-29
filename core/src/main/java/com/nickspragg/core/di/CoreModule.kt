package com.nickspragg.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton


@Module
class CoreModule {

    @Provides
    @Named("ioScheduler")
    fun provideIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named("mainScheduler")
    fun provideAndroidMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}