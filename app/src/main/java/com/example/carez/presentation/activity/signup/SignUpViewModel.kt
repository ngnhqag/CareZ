package com.example.carez.presentation.activity.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.usecase.SignUpWithEmailAndPasswordUseCase
import com.example.carez.domain.usecase.ValidateSignUpInputUseCase
import com.example.carez.presentation.state.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    private val validateSignUpInputUseCase: ValidateSignUpInputUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            validateSignUpInputUseCase(email, password).onFailure { e ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    errorMessage = e.message ?: "Dữ liệu không hợp lệ"
                )
                return@launch
            }

            _state.value = _state.value.copy(isLoading = true)
            signUpWithEmailAndPasswordUseCase(email, password)
                .onSuccess {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null,
                        successMessage = "Đăng ký thành công"
                    )
            }
                .onFailure {  e ->
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