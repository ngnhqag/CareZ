package com.example.carez.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ExerciseEntity (
    @PrimaryKey val id: String = "",
    val name: String = "",
    val localPath: String? = null,
    val remoteUrl: String? = null,
    val isDefault: Boolean = false,
    val lvl: String? = null,
    val isDistanceType: Boolean = false,
    val metBeginner: Float = 0.0F,
    val metPro: Float = 0.0F

)