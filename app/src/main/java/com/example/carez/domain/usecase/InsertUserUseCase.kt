package com.example.carez.domain.usecase

import com.example.carez.domain.model.User
import com.example.carez.domain.repository.UserRepository

class InsertUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = userRepository.insertUser(user)
        return result.fold(
            onSuccess = { true },
            onFailure = { false }
        )
    }
}