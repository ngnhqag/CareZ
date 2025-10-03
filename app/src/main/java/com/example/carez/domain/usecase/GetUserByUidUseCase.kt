package com.example.carez.domain.usecase

import com.example.carez.domain.repository.UserRepository

class GetUserByUidUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String) = userRepository.getUserByUid(uid)
}