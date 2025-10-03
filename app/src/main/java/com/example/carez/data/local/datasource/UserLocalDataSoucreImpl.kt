package com.example.carez.data.local.datasource

import com.example.carez.data.local.dao.UserDao
import com.example.carez.data.local.entities.UserEntity
import com.example.carez.domain.model.User

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {


    override suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override suspend fun getUserByUid(uid: String): UserEntity {
        return userDao.getUserByUid(uid)
    }
}