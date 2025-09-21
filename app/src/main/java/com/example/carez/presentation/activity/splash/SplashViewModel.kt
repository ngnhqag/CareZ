package com.example.carez.presentation.activity.splash

import androidx.lifecycle.ViewModel
import com.example.carez.domain.usecase.CheckSignInUseCase

class SplashViewModel(
    private val checkSignInUseCase: CheckSignInUseCase
) : ViewModel() {
    fun checkSignIn() : Boolean {
        return checkSignInUseCase()
    }
}