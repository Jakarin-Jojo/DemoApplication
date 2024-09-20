package com.jakarin.demoapplication.model

import com.google.gson.annotations.SerializedName

data class PortfolioItem(
    @field:SerializedName("all")
    val all: Double,
    @field:SerializedName("change")
    val change: Double,
    @field:SerializedName("cost")
    val cost: Double,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("enable")
    val enable: Int,
    @field:SerializedName("image_plan")
    val imagePlan: String,
    @field:SerializedName("image_plan_bg")
    val imagePlanBg: String,
    @field:SerializedName("order")
    val order: Int,
    @field:SerializedName("pending_point")
    val pendingPoint: Double,
    @field:SerializedName("plan_id")
    val planId: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("withdrawable_point")
    val withdrawablePoint: Double,
)