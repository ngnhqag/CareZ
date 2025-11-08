package com.example.carez.presentation.activity.exercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carez.databinding.ActivityExerciseBinding
import com.example.carez.databinding.ActivityInfoExerciseBinding

class InfoExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoExerciseBinding

    companion object {
        fun onStart(context: Context) {
            val intent = Intent(context, InfoExerciseActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}