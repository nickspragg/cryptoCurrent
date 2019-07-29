package com.nickspragg.licence.model

import com.google.gson.annotations.SerializedName

data class LicenceData(
    @SerializedName("title")
    val title: String,
    @SerializedName("licence")
    val licence: String
)