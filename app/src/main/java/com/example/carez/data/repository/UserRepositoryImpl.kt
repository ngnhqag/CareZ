package com.example.carez.data.repository

import android.app.Activity
import com.example.carez.data.remote.datasource.GoogleAuthDataSource
import com.example.carez.domain.repository.UserRepository

class UserRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource
) : UserRepository {
    override suspend fun signInWithGoogle(activity: Activity): Boolean {
        return googleAuthDataSource.signIn(activity)
    }

    override fun isSignedIn(): Boolean {
        return googleAuthDataSource.isSignedIn()
    }

    override suspend fun signOut() {
        googleAuthDataSource.signOut()
    }
}

