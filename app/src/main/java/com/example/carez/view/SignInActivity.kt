package com.example.carez.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.auth.GoogleAuthClient
import com.example.carez.databinding.ActivitySignInBinding
import com.example.carez.viewmodel.SignInViewModel
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()
    private val googleAuthClient = GoogleAuthClient(this@SignInActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
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
        binding.btnGoogle.setOnClickListener{
            lifecycleScope.launch {
                val isSuccess = googleAuthClient.signIn()
                if (isSuccess) {
                    MainActivity.onStart(this@SignInActivity)
                }
            }
        }
    }
}