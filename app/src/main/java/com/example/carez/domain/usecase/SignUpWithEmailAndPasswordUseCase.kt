package com.example.carez.domain.usecase

import com.example.carez.domain.model.User
import com.example.carez.domain.repository.UserRepository

class SignUpWithEmailAndPasswordUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return userRepository.signUpWithEmailAndPassword(email, password)
    }
}