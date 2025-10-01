package com.example.carez.presentation.activity.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.carez.databinding.ActivitySignInBinding
import com.example.carez.presentation.activity.main.MainActivity
import com.example.carez.presentation.activity.signup.SignUpActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        observeState()
        signInWithGoogle()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.edtGmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.signInWithEmailAndPassword(email, password)
        }

        binding.txtSignUp.setOnClickListener {
            navigateToSignUp()
        }

    }

    private fun navigateToSignUp() {
            SignUpActivity.onStart(this)
            finish()
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    binding.progressBar.visibility =
                        if (state.isLoading) View.VISIBLE else View.GONE

                    state.successMessage?.let { message ->
                        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
                        navigateToMain()
                    }

                    state.errorMessage?.let { message ->
                        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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