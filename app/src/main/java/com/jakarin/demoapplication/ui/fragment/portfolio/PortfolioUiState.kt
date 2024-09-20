package com.jakarin.demoapplication.ui.fragment.portfolio

import com.jakarin.demoapplication.model.PortfolioItem

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<PortfolioItem>? = null
)
