package com.example.carez.data.remote.datasource

import com.example.carez.data.remote.model.UserFireStore

interface UserRemoteDataSource {
    suspend fun insertUserToFireStore(user: UserFireStore): Result<UserFireStore>
}