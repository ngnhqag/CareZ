package com.example.carez.domain.usecase

import android.app.Activity
import com.example.carez.domain.repository.UserRepository

class SignInWithGoogleUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(activity: Activity): Boolean {
        return userRepository.signInWithGoogle(activity)
    }
}
