package com.example.carez.data.repository.fake

import com.example.carez.domain.model.DailyProgress
import com.example.carez.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProgressRepository : ProgressRepository {

    private val mockData = listOf(
        DailyProgress("2025-10-20", 1640, 2000),
        DailyProgress("2025-10-21", 1850, 2000),
        DailyProgress("2025-10-22", 1930, 2000),
        DailyProgress("2025-10-23", 1930, 2000),
        DailyProgress("2025-10-24", 1580, 2000),
        DailyProgress("2025-10-25", 1580, 2000),
        DailyProgress("2025-10-26", 1810, 2000),
    )

    override suspend fun insertDailyProgress(progress: DailyProgress) {
        // Không làm gì, chỉ giả lập
    }

    override fun getDailyProgress(date: String): Flow<DailyProgress?> = flow {
        emit(mockData.find { it.date == date })
    }

    override fun getMonthlyProgress(month: Int, year: Int): Flow<List<DailyProgress>> = flow {
        emit(mockData)
    }
}
