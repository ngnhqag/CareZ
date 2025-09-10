package com.example.carez.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
class SignInViewModel:ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _signInState = MutableLiveData<Result<String>>()
    val signInState: LiveData<Result<String>> = _signInState

    fun signIn(email:String, password:String) {
        if(email.isEmpty() || password.isEmpty()) {
            _signInState.value = Result.failure((Exception("Vui lòng nhập đầy đủ thông tin")))
            return
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            task -> if (task.isSuccessful) {
                _signInState.value = Result.success(("Đăng nhập thành công"))
            }
            else {
                _signInState.value = Result.failure(task.exception ?: Exception("Đăng nhập thất bại"))
            }
        }
    }

}