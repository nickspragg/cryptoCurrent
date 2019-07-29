package com.nickspragg.licence.di

import android.content.Context
import com.nickspragg.licence.LicenceContract
import com.nickspragg.licence.LicencePresenter
import dagger.Module
import dagger.Provides

@Module
class LicenceModule(private val view: LicenceContract.View, private val context: Context) {

    @Provides
    fun providesContext(): Context = context

    @Provides
    fun providesPresenter(presenter: LicencePresenter): LicenceContract.Presenter = presenter

    @Provides
    fun providesView(): LicenceContract.View = view

}