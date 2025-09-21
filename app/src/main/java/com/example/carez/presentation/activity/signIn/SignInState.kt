package com.example.carez.presentation.activity.signIn

data class SignInState(
    val username: String = "",
    val password: String = "",
    val isSignInSuccess: Boolean = false
)