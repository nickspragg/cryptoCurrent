package com.nickspragg.currentmarket.model

import com.google.gson.annotations.SerializedName

data class Chart (
    @SerializedName("status")
    val status: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("unit")
    val unit: String?,
    @SerializedName("period")
    val period: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("values")
    val values: List<PricePoint>?
) {
    data class PricePoint (
        @SerializedName("x")
        val xValue: String,
        @SerializedName("y")
        val yValue: String
    )
}