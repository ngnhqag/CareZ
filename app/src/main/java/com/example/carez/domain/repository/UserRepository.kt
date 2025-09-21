package com.example.carez.domain.repository

import android.app.Activity

interface UserRepository {
    suspend fun signInWithGoogle(activity: Activity): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut()
}