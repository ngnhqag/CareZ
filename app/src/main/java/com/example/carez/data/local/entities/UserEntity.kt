package com.example.carez.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val uid: String = "",
    val id: String,
    val email: String,
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    val height: Float = 0f,
    val weight: Float = 0f
)