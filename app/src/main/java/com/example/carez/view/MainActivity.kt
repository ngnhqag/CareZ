package com.example.carez.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.carez.R
import com.example.carez.auth.GoogleAuthClient
import com.example.carez.databinding.ActivityMainBinding
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
                    SignInActivity.onStart(this@MainActivity)
                    finish()
            }
        }
    }

}