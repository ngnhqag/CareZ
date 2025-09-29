package com.example.carez.presentation.activity.signup

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
