package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.model.ChartData

interface CurrentMarketContract {
    interface View {
        fun showMarketChart(prices: List<ChartData.PricePoint>)
    }

    interface Presenter {
        fun getMarketChart()

        fun dispose()
    }
}