package com.example.carez.domain.repository

import com.example.carez.domain.model.DailyProgress
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    suspend fun insertDailyProgress(progress: DailyProgress)
    fun getDailyProgress(date: String): Flow<DailyProgress?>
    fun getMonthlyProgress(month: Int, year: Int): Flow<List<DailyProgress>>
}