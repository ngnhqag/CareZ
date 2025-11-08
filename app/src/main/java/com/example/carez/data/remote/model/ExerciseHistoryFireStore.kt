package com.example.carez.data.remote.model

class ExerciseHistoryFireStore (
    val id: String = "",
    val exerciseId: String,
    val date: String,
    val calories: Int,
    val minutes: Int,
    val distance: Float,
)
