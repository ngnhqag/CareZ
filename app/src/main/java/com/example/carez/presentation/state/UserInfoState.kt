package com.example.carez.presentation.state

data class UserInfoState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)