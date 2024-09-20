package com.jakarin.demoapplication.ui.fragment.indices

import com.jakarin.demoapplication.model.Indices

data class IndicesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: Indices? = null,
)