package com.example.carez.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.auth.GoogleAuthClient
import com.example.carez.databinding.ActivitySignInBinding
import com.example.carez.viewmodel.SignInViewModel
import kotlinx.coroutines.launch
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()
    private val googleAuthClient = GoogleAuthClient(this@SignInActivity)

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleSignIn()
        setupListeners()
    }

    private fun observeSignInState() {
        signInViewModel.signInState.observe(this) { result ->
            result.onSuccess { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                MainActivity.onStart(this@SignInActivity)
            }
            result.onFailure{ exception ->
                Toast.makeText(this, exception.message ?:"Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToSignUp() {
            SignUpActivity.onStart(this@SignInActivity)
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

    private fun setupListeners() {
        binding.txtSignUp.setOnClickListener { navigateToSignUp() }

        binding.btnSignIn.setOnClickListener {
            val gmail = binding.edtGmail.text.toString()
            val password = binding.edtPassword.text.toString()
            signInViewModel.signIn(gmail, password)
            observeSignInState()
        }
    }

}