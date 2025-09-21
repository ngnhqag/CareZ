package com.example.carez.data.remote.datasource

import android.app.Activity

interface GoogleAuthDataSource {
    suspend fun signIn(activity: Activity): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut()
}