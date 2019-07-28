package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.model.ChartData

interface CurrentMarketContract {
    interface View {
        fun showMarketChart(prices: List<ChartData.PricePoint>)
        fun showCurrentPrice(price: Double)
        fun showTradeVolume(volume: Double)
        fun showLastUpdated(time: Long)
    }

    interface Presenter {
        fun getMarketChart()
        fun getSummaryStats()

        fun dispose()
    }
}