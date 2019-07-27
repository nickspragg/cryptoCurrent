package com.nickspragg.currentmarket.di

import com.nickspragg.currentmarket.CurrentMarketContract
import com.nickspragg.currentmarket.CurrentMarketPresenter
import com.nickspragg.currentmarket.network.MarketService
import com.nickspragg.currentmarket.network.NetworkConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CurrentMarketModule(private val view: CurrentMarketContract.View) {

    @Provides
    fun providesPresenter(presenter: CurrentMarketPresenter): CurrentMarketContract.Presenter = presenter

    @Provides
    fun providesView(): CurrentMarketContract.View = view

    @Provides
    fun providesMarketService() : MarketService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(NetworkConfig.BASE_URL)
            .build()
        return retrofit.create(MarketService::class.java)
    }
}