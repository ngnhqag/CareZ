package com.example.carez.presentation.activity.signin

data class SignInState(
    val username: String = "",
    val password: String = "",
    val isSignInSuccess: Boolean = false
)