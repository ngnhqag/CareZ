package com.example.carez.data.local.datasource

import com.example.carez.data.local.dao.UserDao
import com.example.carez.data.local.entities.UserEntity

interface UserLocalDataSource {
    suspend fun getUserInfo(userId: String): UserEntity
    suspend fun insertUser(userEntity: UserEntity)
}