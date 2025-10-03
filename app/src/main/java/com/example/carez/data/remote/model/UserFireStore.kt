package com.example.carez.data.remote.model

data class UserFireStore(
    val uid: String = "",
    val email: String = "",
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    val height: Float = 0f,
    val weight: Float = 0f
)