package com.example.carez.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseHistoryEntity(
    @PrimaryKey val id: String = "",
    val exerciseId: String,
    val date: String,
    val calories: Int,
    val minutes: Int,
    val distance: Float,
)

