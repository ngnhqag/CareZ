package com.example.carez.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.model.DailyProgress
import com.example.carez.domain.usecase.GetAverageProgressUseCase
import com.example.carez.domain.usecase.GetMonthlyProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProgressState(
    val isLoading: Boolean = false,
    val data: List<DailyProgress> = emptyList(),
    val averagePercent: Int = 0,
    val error: String? = null
)

class ProgressViewModel(
    private val getMonthlyProgressUseCase: GetMonthlyProgressUseCase,
    private val getAverageProgressUseCase: GetAverageProgressUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProgressState())
    val state: StateFlow<ProgressState> = _state

    fun loadProgress(month: Int, year: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getMonthlyProgressUseCase(month, year).collect { list ->
                    val avg = getAverageProgressUseCase(list)
                    _state.value = ProgressState(
                        isLoading = false,
                        data = list,
                        averagePercent = avg
                    )
                }
            } catch (e: Exception) {
                _state.value = ProgressState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
