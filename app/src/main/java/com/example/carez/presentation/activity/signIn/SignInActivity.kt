package com.example.carez.presentation.activity.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.carez.databinding.ActivitySignInBinding
import com.example.carez.presentation.activity.main.MainActivity
import com.example.carez.presentation.activity.signUp.SignUpActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModel()

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
        handleBindingData()
        signInWithGoogle()
        navigateToSignUp()
    }

    private fun navigateToSignUp() {
        binding.txtSignUp.setOnClickListener {
            SignUpActivity.onStart(this)
            finish()
        }
    }

    private fun handleBindingData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    if (state.isSignInSuccess) {
                        //Save user infor to firestore and room
                        saveUser()
                        navigateToMain()
                    }
                }
            }
        }
    }

    private fun saveUser() {
        TODO("Not yet implemented")
    }

    private fun navigateToMain() {
        MainActivity.onStart(this)
        finish()
    }

    private fun signInWithGoogle() {
        binding.btnGoogle.setOnClickListener {
            viewModel.signInWithGoogle(this)
        }
    }
}