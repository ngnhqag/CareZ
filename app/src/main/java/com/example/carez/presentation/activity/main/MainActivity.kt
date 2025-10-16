package com.example.carez.presentation.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carez.R
import com.example.carez.databinding.ActivityMainBinding
import com.example.carez.presentation.fragment.main.ProgressFragment
import com.example.carez.presentation.fragment.main.HomeFragment
import com.example.carez.presentation.fragment.menu.MenuFragment
import com.example.carez.presentation.fragment.main.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    private fun setupListener() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_progress -> replaceFragment(ProgressFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
                R.id.nav_menu -> replaceFragment(MenuFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}
