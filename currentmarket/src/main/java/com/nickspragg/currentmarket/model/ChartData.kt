package com.nickspragg.currentmarket.model

import com.google.gson.annotations.SerializedName

data class ChartData(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("unit")
    val unit: String? = null,
    @SerializedName("period")
    val period: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("values")
    val values: List<PricePoint>? = emptyList()
) {
    data class PricePoint(
        @SerializedName("x")
        val xValue: Double,
        @SerializedName("y")
        val yValue: Double
    )
}