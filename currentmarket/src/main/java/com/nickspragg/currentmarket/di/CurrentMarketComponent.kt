package com.nickspragg.currentmarket.di

import com.nickspragg.core.di.CoreComponent
import com.nickspragg.core.di.FeatureScope
import com.nickspragg.currentmarket.CurrentMarketActivity
import dagger.Component

@Component(modules = [CurrentMarketModule::class], dependencies = [CoreComponent::class])
@FeatureScope
interface CurrentMarketComponent {
    fun inject(activity: CurrentMarketActivity)
}