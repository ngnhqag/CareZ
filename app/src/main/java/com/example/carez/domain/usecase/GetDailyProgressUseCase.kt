package com.example.carez.domain.usecase

import com.example.carez.domain.model.DailyProgress
import com.example.carez.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow

class GetDailyProgressUseCase(
    private val repository: ProgressRepository
) {
    operator fun invoke(date: String): Flow<DailyProgress?> =
        repository.getDailyProgress(date)
}
