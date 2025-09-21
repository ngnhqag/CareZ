package com.example.carez.presentation.activity.signIn

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.model.User
import com.example.carez.domain.usecase.InsertUserUseCase
import com.example.carez.domain.usecase.SignInWithGoogleUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val firebaseAuth: FirebaseAuth
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun signInWithGoogle(activity: Activity) {
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = signInWithGoogleUseCase(activity)
            if (isSuccess) {
                val user = User(
                    id = firebaseAuth.currentUser?.uid ?: "",
                    name = firebaseAuth.currentUser?.displayName ?: "",
                    email = firebaseAuth.currentUser?.email ?: "",
                )
                val result = insertUserUseCase(user)
                if (result) {
                    _state.update { signInState ->
                        signInState.copy(
                            isSignInSuccess = isSuccess
                        )
                    }
                }
            }
        }
    }
}

