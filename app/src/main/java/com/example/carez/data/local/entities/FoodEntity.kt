package com.example.carez.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodEntity(
    @PrimaryKey val id: String = "",
    val name: String,
    val gram: Int,
    val calo: Int,
    val lipid: Float,
    val fiber: Int,
    val protein: Int,
    val sugar: Int,
    val salt: Int,
    val water: Int,
    val localPath: String? = null,
    val remoteUrl: String? = null,
    val isDefault: Boolean = false
)