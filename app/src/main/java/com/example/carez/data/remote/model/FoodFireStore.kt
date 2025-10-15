package com.example.carez.data.remote.model

data class FoodFireStore (
    val id: String = "",
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
    val remoteUrl: String? = null
)
