package com.example.carez.data.repository

import android.app.Activity
import com.example.carez.data.local.datasource.UserLocalDataSource
import com.example.carez.data.mapper.toEntity
import com.example.carez.data.mapper.toFireStore
import com.example.carez.data.remote.datasource.GoogleAuthDataSource
import com.example.carez.data.remote.datasource.UserRemoteDataSource
import com.example.carez.domain.model.User
import com.example.carez.domain.repository.UserRepository

class UserRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun signInWithGoogle(activity: Activity): Boolean {
        return googleAuthDataSource.signIn(activity)
    }

    override fun isSignedIn(): Boolean {
        return googleAuthDataSource.isSignedIn()
    }

    override suspend fun signOut(activity: Activity) {
        googleAuthDataSource.signOut(activity)
    }

    override suspend fun insertUser(user: User): Result<Boolean> {
        return try {
            val userFireStore = user.toFireStore()
            val result = userRemoteDataSource.insertUserToFireStore(userFireStore)

            result.fold(
                onSuccess = { remoteUser ->
                    userLocalDataSource.insertUser(remoteUser.toEntity())
                    Result.success(true)
                },
                onFailure = { e ->
                    Result.failure(e)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

