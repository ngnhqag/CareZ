package com.example.carez.presentation.activity.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.databinding.ActivitySignUpBinding
import com.example.carez.presentation.activity.signin.SignInActivity
import kotlinx.coroutines.flow.collectLatest
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModel()

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        observeState()
    }

    private fun setupListeners() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.edtGmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            viewModel.signUp(email, password)
        }

        binding.txtSignIn.setOnClickListener {
            Log.d("Signupactvt", "txtsignin")
            navigateToSignIn()
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

                    state.successMessage?.let { message ->
                        Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                        navigateToSignIn()
                    }

                    state.errorMessage?.let { message ->
                        Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun navigateToSignIn() {
        SignInActivity.onStart(this)
        finish()
    }
}