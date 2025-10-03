package com.example.carez.data.local.datasource

import com.example.carez.data.local.dao.UserDao
import com.example.carez.data.local.entities.UserEntity
import com.example.carez.domain.model.User

interface UserLocalDataSource {
    suspend fun insertUser(userEntity: UserEntity)
    suspend fun getUserByUid(uid: String): UserEntity
}