package com.example.carez.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUp(id: String, email:String, password:String, onResult:(Boolean,String) -> Unit){

        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null) {
                            saveUserToFirestore(id,email,user.uid,onResult)
                        }
                        else{
                            onResult(false,task.exception?.message ?: "Đăng ký thất bại")
                        }
                    }
                }
        }

    }

    private fun saveUserToFirestore(id: String, email: String, uid: String, onResult: (Boolean, String) -> Unit) {
        val userMap = hashMapOf(
            "id" to id,
            "email" to email,
            "uid" to uid,
        )

        db.collection("user").document(uid)  // tạo 1 collection chứa user và collection chứa document có tên là uid
            .set(userMap)
            .addOnSuccessListener {
                onResult(true, "Đăng ký thành công")
            }
            .addOnFailureListener { e ->
                onResult(false, "Lỗi lưu dữ liệu: ${e.message}")
            }
    }
}