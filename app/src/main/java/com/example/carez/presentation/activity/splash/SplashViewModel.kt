package com.example.carez.presentation.activity.splash

import android.util.Log
import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.usecase.CheckSignInUseCase
import com.example.carez.domain.usecase.CheckUserInfoUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkSignInUseCase: CheckSignInUseCase,
    private val checkUserInfoUseCase: CheckUserInfoUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    fun checkUser(
        onNotSignedIn: () -> Unit,
        onSignedInWithInfo: () -> Unit,
        onSignedInWithoutInfo: () -> Unit
    ) {
        val uid = firebaseAuth.currentUser?.uid

        if (!checkSignInUseCase() || uid.isNullOrEmpty()) {
            onNotSignedIn()
        } else {
            viewModelScope.launch {
                try {
                    val result = checkUserInfoUseCase(uid)
                    Log.d("SplashVM", "checkUserInfo result = $result")

                    if (result.isSuccess && result.getOrNull() == true) {
                        Log.d("SplashVM", "User info complete = true")
                        onSignedInWithInfo()
                    } else {
                        Log.d("SplashVM", "User info complete = false")
                        onSignedInWithoutInfo()
                    }
                } catch (e: Exception) {
                    Log.e("SplashVM", "checkUserInfo failed", e)
                    onNotSignedIn()
                }
            }
        }
    }
}
