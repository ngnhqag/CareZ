package com.example.carez.presentation.activity.signIn

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun signInWithGoogle(activity: Activity) {
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = signInWithGoogleUseCase(activity)
            _state.update { signInState ->
                signInState.copy(
                    isSignInSuccess = isSuccess
                )
            }
        }
    }
}

