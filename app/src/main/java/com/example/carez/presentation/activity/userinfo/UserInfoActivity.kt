package com.example.carez.presentation.activity.userinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityUserInfoBinding
import com.example.carez.domain.model.User
import com.example.carez.presentation.activity.splash.SplashActivity
import com.example.carez.presentation.fragment.userinfo.AgeFragment
import com.example.carez.presentation.fragment.userinfo.GenderFragment
import com.example.carez.presentation.fragment.userinfo.HeightFragment
import com.example.carez.presentation.fragment.userinfo.NameFragment
import com.example.carez.presentation.fragment.userinfo.WeightFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity() {

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, UserInfoActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityUserInfoBinding

    private val viewModel: UserInfoViewModel by viewModel()

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
            } else {
                val name = (fragments[0] as NameFragment).getName()
                val gender = (fragments[1] as GenderFragment).getGender()
                val height = (fragments[2] as HeightFragment).getHeight()
                val age = (fragments[3] as AgeFragment).getAge()
                val weight = (fragments[4] as WeightFragment).getWeight()

                if (name != null && gender != null && height != null && weight != null) {
                    val user = User(
                        name = name,
                        gender = gender,
                        height = height.toFloat(),
                        weight = weight,
                        age = age
                    )

                    viewModel.saveUser(user) { success ->
                        if (success) {
                            SplashActivity.onStart(this)
                            finish()
                        } else {
                            Toast.makeText(this, "Lưu dữ liệu thất bại", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.btnBack.setOnClickListener {
            if (currentStep > 0) {
                currentStep--
                showFragment(currentStep)
            }
        }
    }
    private fun showFragment(index: Int) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragments[index])
            .commit()

        binding.btnContinue.text = if (index == fragments.size - 1) "Hoàn tất" else "Tiếp tục"
        binding.btnBack.isEnabled = index > 0

        binding.txtTitle.text = when (index) {
            0 -> "Tên của bạn là:"
            1 -> "Giới tính của bạn là?"
            2 -> "Chiều cao của bạn:"
            3 -> "Tuổi của bạn:"
            4 -> "Cân nặng của bạn:"
            else -> ""
        }
    }
}
