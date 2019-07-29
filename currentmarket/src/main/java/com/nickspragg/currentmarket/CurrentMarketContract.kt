package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.model.ChartData

interface CurrentMarketContract {
    interface View {
        fun setChartData(prices: List<ChartData.PricePoint>)
        fun setCurrentPrice(price: Double)
        fun setTradeVolume(volume: Double)
        fun setLastUpdated(time: Long)

        fun showPlaceholderSummaryView(show: Boolean)
        fun showSummaryView(show: Boolean)
        fun showErrorSummaryView(show: Boolean)

        fun hideIsRefreshing()
    }

    interface Presenter {
        fun getMarketChart(isRefresh: Boolean = false)
        fun getSummaryStats(isRefresh: Boolean = false)

        fun dispose()
    }
}