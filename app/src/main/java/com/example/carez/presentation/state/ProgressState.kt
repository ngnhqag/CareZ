package com.example.carez.presentation.state

import com.example.carez.domain.model.DailyProgress

data class ProgressState(
    val isLoading: Boolean = false,
    val data: List<DailyProgress> = emptyList(),
    val averagePercent: Int = 0,
    val error: String? = null
)