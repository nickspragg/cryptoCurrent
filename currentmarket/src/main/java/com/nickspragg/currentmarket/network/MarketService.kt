package com.nickspragg.currentmarket.network

import com.nickspragg.currentmarket.model.ChartData
import com.nickspragg.currentmarket.model.StatsData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MarketService {

    @GET("charts/transactions-per-second")
    fun fetchTransactionsPerSecond(
        @Query("timespan") timespan: String,
        @Query("rollingAverage") rollingAverage: String? = null,
        @Query("start") start: String? = null,
        @Query("format") format: String = "json"): Single<Response<ChartData>>

    @GET("charts/market-price")
    fun fetchMarketPrice(
        @Query("timespan") timespan: String = "30days",
        @Query("rollingAverage") rollingAverage: String? = null,
        @Query("start") start: String? = null,
        @Query("format") format: String = "json"): Single<Response<ChartData>>

    @GET("stats")
    fun fetchStats(): Single<Response<StatsData>>
}
