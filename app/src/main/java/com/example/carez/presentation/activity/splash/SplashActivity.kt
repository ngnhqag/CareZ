package com.example.carez.presentation.activity.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.R
import com.example.carez.presentation.activity.main.MainActivity
import com.example.carez.presentation.activity.signIn.SignInActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.logger.Logger

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(1500)
            handleCheckSignIn()
        }
    }

    private fun handleCheckSignIn() {
        if (viewModel.checkSignIn()) {
            MainActivity.onStart(this@SplashActivity)
        } else {
            SignInActivity.onStart(this@SplashActivity)
        }
    }
}