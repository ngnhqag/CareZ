package com.example.carez.presentation.activity.main

import android.content.Context
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.carez.R
import com.example.carez.databinding.ActivityMainBinding
import com.example.carez.presentation.fragment.progress.ProgressFragment
import com.example.carez.presentation.fragment.main.HomeFragment
import com.example.carez.presentation.fragment.menu.MenuFragment
import com.example.carez.presentation.fragment.main.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var clicked = false

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
        setupFabItemClicks()
        closeFabMenu()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Tắt menu khi chạm nền mờ
        binding.overlayBackground.setOnClickListener {
            closeFabMenu()
        }
    }

    private fun onAddButtonClicked() {
        binding.fABAdd.setOnClickListener {
            setVisibility(!clicked)
            setAnimation(!clicked)
            clicked = !clicked
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (clicked) {
            binding.layoutFabGroup.visibility = View.VISIBLE
            binding.overlayBackground.visibility = View.VISIBLE

            // Làm mờ phần fragment
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.frameLayout.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        30f, 30f, Shader.TileMode.CLAMP
                    )
                )
            } else {
                // Fallback Android < 12
                binding.overlayBackground.setBackgroundColor(0x80000000.toInt())
            }
        } else {
            binding.layoutFabGroup.visibility = View.GONE
            binding.overlayBackground.visibility = View.GONE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.frameLayout.setRenderEffect(null)
            } else {
                binding.overlayBackground.setBackgroundColor(0x00000000)
            }
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (clicked) {
            binding.fABAdd.startAnimation(rotateOpen)
            binding.fABAddMeal.extend()
            binding.fABDoExercise.extend()
            binding.fABDrinkWater.extend()
            binding.fABAddMeal.startAnimation(fromBottom)
            binding.fABDoExercise.startAnimation(fromBottom)
            binding.fABDrinkWater.startAnimation(fromBottom)

            // Fade overlay in
            binding.overlayBackground.alpha = 0f
            binding.overlayBackground.animate().alpha(1f).setDuration(250).start()

        } else {
            binding.fABAdd.startAnimation(rotateClose)
            binding.fABAddMeal.shrink()
            binding.fABDoExercise.shrink()
            binding.fABDrinkWater.shrink()
            binding.fABAddMeal.startAnimation(toBottom)
            binding.fABDoExercise.startAnimation(toBottom)
            binding.fABDrinkWater.startAnimation(toBottom)

            // Fade overlay out
            binding.overlayBackground.animate().alpha(0f).setDuration(200)
                .withEndAction { binding.overlayBackground.visibility = View.GONE }
                .start()
        }
    }

    private fun setupListener() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            // Khi người dùng chọn item mới → tắt menu FAB nếu đang mở
            if (clicked) {
                closeFabMenu()
            }

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

    private fun setupFabItemClicks() {
        binding.fABDoExercise.setOnClickListener {

        }

        binding.fABAddMeal.setOnClickListener {
            replaceFragment(MenuFragment())
            closeFabMenu()
        }

        binding.fABDrinkWater.setOnClickListener {

        }
    }

    private fun closeFabMenu() {
        setVisibility(false)
        setAnimation(false)
        clicked = false
    }
}
