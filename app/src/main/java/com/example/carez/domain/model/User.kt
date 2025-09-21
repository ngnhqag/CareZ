package com.example.carez.domain.model

data class User(
    val uid: String = "",
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    val height: Float = 0f,
    val weight: Float = 0f
)