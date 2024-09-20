package com.jakarin.demoapplication.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.jakarin.demoapplication.model.Indices
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IndicesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getIndicesData(): Indices {
        return try {
            val jsonString = context.assets.open("indices.json").use { inputStream ->
                inputStream.bufferedReader().use { it.readText() }
            }
            Gson().fromJson(jsonString, Indices::class.java)
        } catch (e: Exception) {
            Log.e("getIndicesData", "Error reading indices.json: ${e.message}", e)
            throw e
        }
    }
}