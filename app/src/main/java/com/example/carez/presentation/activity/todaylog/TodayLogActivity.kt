package com.example.carez.presentation.activity.todaylog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityTodayLogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.carez.R
import com.example.carez.presentation.activity.main.MainActivity

class TodayLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodayLogBinding

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, TodayLogActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Lấy danh sách món đã ăn hôm nay từ DB
        // TODO: Lấy danh sách tập luyện hôm nay từ DB

        // Gắn adapter cho RecyclerView sau này

    }
}
