package com.jakarin.demoapplication.repository

import android.content.Context
import com.google.gson.Gson
import com.jakarin.demoapplication.model.Indices
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class IndicesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    suspend fun getIndicesData(): Indices {
        return try {
            val inputStream = context.assets.open("indices.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, Indices::class.java)
        } catch (e: Exception) {
            throw e
        }
    }
}