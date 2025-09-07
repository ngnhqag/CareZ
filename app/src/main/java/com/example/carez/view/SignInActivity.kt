package com.example.carez.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.SignInBinding
import com.example.carez.viewmodel.SignInViewModel
import androidx.activity.viewModels
import android.content.Intent
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.carez.auth.GoogleAuthClient
import com.example.carez.view.SignUpActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: SignInBinding
    private val signInViewModel: SignInViewModel by viewModels()
    private val googleAuthClient = GoogleAuthClient(this@SignInActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleSignIn()
        navigateToSignUp()
    }

    private fun navigateToSignUp() {
        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleSignIn() {
        binding.btnSignIn.setOnClickListener{
            lifecycleScope.launch {
                googleAuthClient.signIn()
            }
        }
    }

}