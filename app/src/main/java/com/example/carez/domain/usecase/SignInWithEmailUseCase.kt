package com.example.carez.domain.usecase

import com.example.carez.domain.repository.UserRepository

class SignInWithEmailUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return repository.signInWithEmail(email, password)
    }
}