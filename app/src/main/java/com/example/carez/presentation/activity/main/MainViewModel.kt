package com.example.carez.presentation.activity.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.usecase.SignOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    fun signOut(activity: Activity) {
        viewModelScope.launch(Dispatchers.IO) {
            signOutUseCase(activity)
        }
    }
}