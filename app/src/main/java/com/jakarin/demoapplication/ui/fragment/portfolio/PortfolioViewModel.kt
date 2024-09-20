package com.jakarin.demoapplication.ui.fragment.portfolio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakarin.demoapplication.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioRepository: PortfolioRepository,
) : ViewModel() {
    private val _portfolioUiState = MutableLiveData<PortfolioUiState>()
    val portfolioUiState: LiveData<PortfolioUiState>
        get() = _portfolioUiState

    init {
        loadData()
    }

    private fun loadData() {
        _portfolioUiState.value = PortfolioUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val data = portfolioRepository.getPortfolio()
                _portfolioUiState.value = (PortfolioUiState(data = data, isLoading = false))
            } catch (e: Exception) {
                _portfolioUiState.value = (PortfolioUiState(error = e.message, isLoading = false))
                Log.e("PortfolioViewModel", "Error loading portfolio", e)
            }
        }
    }
}