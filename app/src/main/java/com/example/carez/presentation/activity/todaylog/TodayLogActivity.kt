package com.example.carez.presentation.activity.todaylog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityTodayLogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.carez.R

class TodayLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodayLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Lấy danh sách món đã ăn hôm nay từ DB
        // TODO: Lấy danh sách tập luyện hôm nay từ DB

        // Gắn adapter cho RecyclerView sau này

    }

    private fun showAddActivityDialog() {
        val options = arrayOf("🍽️ Ghi lại bữa ăn", "🏋️ Ghi lại tập luyện")

        AlertDialog.Builder(this)
            .setTitle("Thêm hoạt động")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        // TODO: Chuyển sang màn hình ghi lại bữa ăn
                    }
                    1 -> {
                        // TODO: Chuyển sang màn hình ghi lại tập luyện
                    }
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}
