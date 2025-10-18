package com.example.carez.presentation.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this@MainActivity, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this@MainActivity, R.anim.to_bottom_anim) }

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
        onAddButtonClicked()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (clicked) {
            binding.fABAddMeal.visibility = View.VISIBLE
            binding.fABDoExercise.visibility = View.VISIBLE
            binding.fABDrinkWater.visibility = View.VISIBLE
        } else {
            binding.fABAddMeal.visibility = View.INVISIBLE
            binding.fABDoExercise.visibility = View.INVISIBLE
            binding.fABDrinkWater.visibility = View.INVISIBLE
        }
    }
    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fABAddMeal.startAnimation(fromBottom)
            binding.fABDoExercise.startAnimation(fromBottom)
            binding.fABDrinkWater.startAnimation(fromBottom)
            binding.fABAdd.startAnimation(rotateOpen)
        } else {
            binding.fABAddMeal.startAnimation(toBottom)
    }

    private fun onAddButtonClicked() {
        binding.fABAdd.setOnClickListener {
            setVisibility(true)
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
