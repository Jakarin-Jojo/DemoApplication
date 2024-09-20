package com.jakarin.demoapplication.ui.fragment.indices

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakarin.demoapplication.repository.IndicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class IndicesViewModel @Inject constructor(
    private val indicesRepository: IndicesRepository,
) : ViewModel() {
    private val _indicesLivedata = MutableLiveData<IndicesUiState>()
    val indicesLiveData: LiveData<IndicesUiState>
        get() = _indicesLivedata
    private val _lastUpdateLivedata = MutableLiveData<String>()
    val lastUpdateLiveData: LiveData<String>
        get() = _lastUpdateLivedata

    init {
        loadData()
    }

    private fun loadData() {
        _indicesLivedata.value = IndicesUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val data = indicesRepository.getIndicesData()
                _lastUpdateLivedata.value = "Last Update ${formatDate(data.lastUpdate)}"
                _indicesLivedata.value = IndicesUiState(data = data)
            } catch (e: Exception) {
                Log.e("IndicesViewModel", "${e.message}")
                _indicesLivedata.value = IndicesUiState(error = e.message)
            }
        }
    }

    private fun formatDate(dateString: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())

        return try {
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}