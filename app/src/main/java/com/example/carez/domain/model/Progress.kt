package com.example.carez.domain.model

data class Progress (
    val month: Int,
    val year: Int,
    val dailyProgress: List<DailyProgress>
)