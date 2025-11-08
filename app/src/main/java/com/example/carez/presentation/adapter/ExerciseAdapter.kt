package com.example.carez.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carez.R
import com.example.carez.databinding.ItemExerciseBinding
import com.example.carez.domain.model.Exercise
import com.example.carez.presentation.util.loadFoodImage

class ExerciseAdapter(
    private val onItemClick: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private val listExercise = mutableListOf<Exercise>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = listExercise[position]
        with(holder.binding) {
            txtExerciseName.text = exercise.name
            txtExerciseCalories.text = exercise.calo
            imgExerciseIcon.loadFoodImage(exercise.localPath, exercise.remoteUrl, R.drawable.ic_running)
            root.setOnClickListener { onItemClick(exercise) }
        }
    }

    override fun getItemCount(): Int = listExercise.size

    inner class ExerciseViewHolder(val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root)
}