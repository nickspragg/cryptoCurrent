package com.nickspragg.currentmarket.model

import com.google.gson.annotations.SerializedName

data class StatsData(
    @SerializedName("market_price_usd")
    val marketPriceUsd: Double,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("trade_volume_btc")
    val tradeVolumeBtc: Double,
    @SerializedName("trade_volume_usd")
    val tradeVolumeUsd: Double
)