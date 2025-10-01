package com.example.carez.data.repository

import android.app.Activity
import com.example.carez.data.local.datasource.UserLocalDataSource
import com.example.carez.data.mapper.toDomain
import com.example.carez.data.mapper.toEntity
import com.example.carez.data.mapper.toFireStore
import com.example.carez.data.remote.datasource.GoogleAuthDataSource
import com.example.carez.data.remote.datasource.UserRemoteDataSource
import com.example.carez.domain.model.User
import com.example.carez.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
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

    override suspend fun getUserByUid(uid: String): Result<User> {
        return try {
            val localUser = userLocalDataSource.getUserByUid(uid)
            Result.success(localUser.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun syncUserInfo(uid: String): Result<User> {
       return try {
        val remote = userRemoteDataSource.getUserFromFireStore(uid).getOrThrow()
        val entity = remote.toEntity()
        userLocalDataSource.insertUser(entity)
        Result.success(entity.toDomain())
       } catch (e : Exception) {
           Result.failure(e)
       }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<User> {
        return userRemoteDataSource.signUpWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {
        return userRemoteDataSource.signInWithEmailAndPassword(email, password)
    }


}

