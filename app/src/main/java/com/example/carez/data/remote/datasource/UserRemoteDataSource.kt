package com.example.carez.data.remote.datasource

import com.example.carez.data.remote.model.UserFireStore
import com.example.carez.domain.model.User

interface UserRemoteDataSource {
    suspend fun insertUserToFireStore(user: UserFireStore): Result<UserFireStore>
    suspend fun getUserFromFireStore(userId: String): Result<UserFireStore>
    suspend fun signUpWithEmailAndPassword(email: String, password:String): Result<User>
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User>
}