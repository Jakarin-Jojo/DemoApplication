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
    private val _portfolioPortfolioUiState = MutableLiveData<PortfolioUiState>()
    val portfolioUiState: LiveData<PortfolioUiState>
        get() = _portfolioPortfolioUiState

    init {
        loadData()
    }

    private fun loadData() {
        _portfolioPortfolioUiState.value = PortfolioUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val data = portfolioRepository.getPortfolio()
                _portfolioPortfolioUiState.value = (PortfolioUiState(data = data))
            } catch (e: Exception) {
                _portfolioPortfolioUiState.value = (PortfolioUiState(error = e.message))
                Log.e("IndicesViewModel", "${e.message}")
            }
        }
    }
}