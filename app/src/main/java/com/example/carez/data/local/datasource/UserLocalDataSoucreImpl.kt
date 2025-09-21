package com.example.carez.data.local.datasource

import com.example.carez.data.local.dao.UserDao
import com.example.carez.data.local.entities.UserEntity

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUserInfo(userId: String): UserEntity {
        return userDao.getUser(userId)
    }

    override suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }
}