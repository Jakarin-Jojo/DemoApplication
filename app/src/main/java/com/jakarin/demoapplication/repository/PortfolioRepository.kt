package com.jakarin.demoapplication.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jakarin.demoapplication.model.PortfolioItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PortfolioRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getPortfolio(): List<PortfolioItem> {
        return try {
            val inputStream = context.assets.open("portfolio.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<PortfolioItem>>() {}.type
            Gson().fromJson(jsonString, listType)
        } catch (e: Exception) {
            Log.e("getIndicesData", "File not found: ${e.message}")
            throw e
        }
    }
}