package com.example.carez.domain.usecase

import com.example.carez.domain.model.DailyProgress

class GetAverageProgressUseCase {
    operator fun invoke(list: List<DailyProgress>): Int {
        if (list.isEmpty()) return 0
        val totalPercent = list.sumOf {
            (it.calories * 100) / it.goal
        }
        return totalPercent / list.size
    }
}
