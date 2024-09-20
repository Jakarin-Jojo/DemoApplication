package com.jakarin.demoapplication.model

import com.google.gson.annotations.SerializedName

data class Indices(
    @field:SerializedName("data")
    val data: List<Data>,
    @field:SerializedName("last_update")
    val lastUpdate: String,
)