package com.example.carez.presentation.activity.signin

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.model.User
import com.example.carez.domain.usecase.InsertUserUseCase
import com.example.carez.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.example.carez.domain.usecase.SignInWithGoogleUseCase
import com.example.carez.presentation.state.SignInState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
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
                            isSuccess = isSuccess
                        )
                    }
                }
            }
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                errorMessage = null,
                successMessage = null
            )

            signInWithEmailAndPasswordUseCase(email, password)
                .onSuccess { user ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null,
                        successMessage = "Đăng nhập thành công"
                    )
                }
                .onFailure { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = e.message,
                        successMessage = null
                    )
                }
        }
    }


}

