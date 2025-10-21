package com.example.carez.domain.usecase

import com.example.carez.domain.model.DailyProgress
import com.example.carez.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow

class GetMonthlyProgressUseCase(
    private val repository: ProgressRepository
) {
    operator fun invoke(month: Int, year: Int): Flow<List<DailyProgress>> =
        repository.getMonthlyProgress(month, year)
}
