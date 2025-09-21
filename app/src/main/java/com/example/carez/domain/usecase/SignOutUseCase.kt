package com.example.carez.domain.usecase

import android.app.Activity
import com.example.carez.domain.repository.UserRepository

class SignOutUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(activity: Activity) {
        return userRepository.signOut(activity)
    }
}

