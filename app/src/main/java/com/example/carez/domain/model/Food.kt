package com.example.carez.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: String = "",
    val name: String,
    val category: String,
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
) : Parcelable
