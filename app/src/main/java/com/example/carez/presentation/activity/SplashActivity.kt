package com.example.carez.presentation.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.R
import com.example.carez.presentation.activity.signIn.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SplashActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        checkSigned()
    }

    private fun checkSigned() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            SignInActivity.onStart(this)
//            else {
//
//            }
        }
    }
}