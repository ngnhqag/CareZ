package com.example.carez.presentation.activity.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.R
import com.example.carez.presentation.activity.userinfo.UserInfoActivity
import com.example.carez.presentation.activity.main.MainActivity
import com.example.carez.presentation.activity.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1500)
            viewModel.checkUser(
                onNotSignedIn = {
                    SignInActivity.onStart(this@SplashActivity)
                },
                onSignedInWithInfo = {
                    MainActivity.onStart(this@SplashActivity)
                },
                onSignedInWithoutInfo = {
                    UserInfoActivity.onStart(this@SplashActivity)
                }
            )
        }
    }
}