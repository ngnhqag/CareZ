package com.example.carez.domain.model

data class ExerciseHistory (
    val id: String = "",
    val exerciseId: String,
    val date: String,
    val calories: Int,
    val timeMinutes: Int,
    val distance: Float,
    val lvl: String
)