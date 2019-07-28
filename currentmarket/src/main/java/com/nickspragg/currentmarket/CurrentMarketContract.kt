package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.model.ChartData

interface CurrentMarketContract {
    interface View {
        fun showMarketChart(prices: List<ChartData.PricePoint>)
        fun showCurrentPrice(price: Double)
        fun showTradeVolume(volume: Double)
        fun showLastUpdated(time: Long)

        fun hideIsRefreshing()
    }

    interface Presenter {
        fun getMarketChart(isRefresh: Boolean = false)
        fun getSummaryStats(isRefresh: Boolean = false)

        fun dispose()
    }
}