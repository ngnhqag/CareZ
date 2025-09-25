package com.example.carez.domain.repository

import android.app.Activity
import com.example.carez.domain.model.User

interface UserRepository {
    suspend fun signInWithGoogle(activity: Activity): Boolean
    suspend fun signInWithEmail(email: String, password: String): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut(activity: Activity)
    suspend fun insertUser(user: User): Result<Boolean>
    suspend fun getUserByUid(uid: String): Result<User>
    suspend fun syncUserInfo(uid: String): Result<User>


}