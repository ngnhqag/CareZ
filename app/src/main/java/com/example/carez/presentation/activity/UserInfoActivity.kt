package com.example.carez.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityUserInfoBinding
import com.example.carez.presentation.activity.main.MainActivity
import com.example.carez.presentation.fragment.AgeFragment
import com.example.carez.presentation.fragment.GenderFragment
import com.example.carez.presentation.fragment.HeightFragment
import com.example.carez.presentation.fragment.NameFragment
import com.example.carez.presentation.fragment.WeightFragment

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private val fragments = listOf(
        NameFragment(),
        GenderFragment(),
        HeightFragment(),
        AgeFragment(),
        WeightFragment()
    )
    private var currentStep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(currentStep)

        binding.btnContinue.setOnClickListener {
            if (currentStep < fragments.size - 1) {
                currentStep++
                showFragment(currentStep)
            }
            else {
                finishUserInfo()
            }
        }

        binding.btnBack.setOnClickListener {
            if (currentStep > 0) {
                currentStep--
                showFragment(currentStep)
            }
        }


    }

    private fun finishUserInfo() {
        MainActivity.onStart(this@UserInfoActivity)
        finish()
    }

    private fun showFragment(index: Int) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragments[index])
            .commit()

        binding.btnContinue.text = if (index == fragments.size - 1) "Hoàn tất" else "Tiếp tục"
        binding.btnBack.isEnabled = index > 0

    }

}