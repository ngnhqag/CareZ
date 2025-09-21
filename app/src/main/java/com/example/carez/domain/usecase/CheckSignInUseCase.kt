package com.example.carez.domain.usecase

import com.example.carez.domain.repository.UserRepository

class CheckSignInUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        return userRepository.isSignedIn()
    }
}