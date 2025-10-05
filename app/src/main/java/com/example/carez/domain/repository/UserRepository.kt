package com.example.carez.domain.repository

import android.app.Activity
import com.example.carez.domain.model.User

interface UserRepository {
    suspend fun signInWithGoogle(activity: Activity): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut(activity: Activity)
    suspend fun insertUser(user: User): Result<Boolean>
    suspend fun getUserByUid(uid: String): Result<User>
    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<User>
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User>
    suspend fun checkUserInfo(uid: String): Result<Boolean>

}