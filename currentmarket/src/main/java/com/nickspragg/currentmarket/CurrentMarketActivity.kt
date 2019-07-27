package com.nickspragg.currentmarket

import android.app.Activity
import android.os.Bundle
import com.nickspragg.core.di.CoreInjectHelper
import com.nickspragg.currentmarket.di.CurrentMarketModule
import com.nickspragg.currentmarket.di.DaggerCurrentMarketComponent
import com.nickspragg.currentmarket.model.Chart
import javax.inject.Inject

class CurrentMarketActivity : Activity(), CurrentMarketContract.View {

    @Inject
    lateinit var presenter: CurrentMarketContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCurrentMarketComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .currentMarketModule(CurrentMarketModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getMarketChart()
    }

    override fun showMarketChart(prices: List<Chart.PricePoint>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}