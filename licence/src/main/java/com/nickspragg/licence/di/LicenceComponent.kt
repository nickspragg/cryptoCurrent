package com.nickspragg.currentmarket.di

import com.nickspragg.core.di.CoreComponent
import com.nickspragg.core.di.FeatureScope
import com.nickspragg.licence.LicenceActivity
import com.nickspragg.licence.di.LicenceModule
import dagger.Component

@Component(modules = [LicenceModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface LicenceComponent {
    fun inject(activity: LicenceActivity)
}