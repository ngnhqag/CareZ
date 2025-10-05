package com.example.carez.presentation.activity.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.model.User
import com.example.carez.domain.usecase.InsertUserUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class UserInfoState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)

class UserInfoViewModel(
    private val insertUserUseCase: InsertUserUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(UserInfoState())
    val state = _state.asStateFlow()

    fun saveUser(
        user: User,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val current = firebaseAuth.currentUser
            if (current == null) {
                withContext(Dispatchers.Main) { onResult(false) }
                return@launch
            }
            val userToSave = user.copy(
                uid = current.uid,
                email = current.email ?: ""
            )
            val saved = insertUserUseCase(userToSave)
            withContext(Dispatchers.Main) {
                onResult(saved)
            }
        }
    }
}