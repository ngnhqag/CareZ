package com.example.carez.domain.usecase

import com.example.carez.domain.repository.UserRepository

class CheckUserInfoUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String): Result<Boolean> {
        return userRepository.checkUserInfo(uid)
    }
}
