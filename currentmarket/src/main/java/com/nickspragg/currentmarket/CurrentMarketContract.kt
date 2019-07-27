package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.model.Chart

interface CurrentMarketContract {
    interface View {
        fun showMarketChart(prices: List<Chart.PricePoint>)
    }

    interface Presenter {
        fun getMarketChart()

        fun dispose()
    }
}