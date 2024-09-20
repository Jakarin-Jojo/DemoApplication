package com.jakarin.demoapplication.model

import com.google.gson.annotations.SerializedName

data class Data(
    @field:SerializedName("change")
    val change: Double,
    @field:SerializedName("percent_change")
    val percentChange: Double,
    @field:SerializedName("price")
    val price: Double,
    @field:SerializedName("short_name")
    val shortName: String,
    @field:SerializedName("symbol")
    val symbol: String,
)