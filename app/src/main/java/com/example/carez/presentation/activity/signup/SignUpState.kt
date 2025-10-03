package com.example.carez.presentation.activity.signup

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null

)
