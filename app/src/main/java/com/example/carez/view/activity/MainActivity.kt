package com.example.carez.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.carez.auth.GoogleAuthClient
import com.example.carez.databinding.ActivityMainBinding
import com.example.carez.view.activity.SignInActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding
    private var googleAuthClient = GoogleAuthClient(this@MainActivity)

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
        setupListeners()
    }

    private fun setupListeners() {
        biding.btnSignOut.setOnClickListener {
            lifecycleScope.launch {
            googleAuthClient.signOut()
                    SignInActivity.Companion.onStart(this@MainActivity)
                    finish()
            }
        }
    }

}