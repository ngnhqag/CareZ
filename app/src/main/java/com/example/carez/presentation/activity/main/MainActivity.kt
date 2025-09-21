package com.example.carez.presentation.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding

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

        }
    }
}