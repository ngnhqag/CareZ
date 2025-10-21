package com.example.carez.domain.usecase

import com.example.carez.domain.model.DailyProgress
import com.example.carez.domain.repository.ProgressRepository

class InsertDailyProgressUseCase(
    private val repository: ProgressRepository
) {
    suspend operator fun invoke(progress: DailyProgress) {
        repository.insertDailyProgress(progress)
    }
}