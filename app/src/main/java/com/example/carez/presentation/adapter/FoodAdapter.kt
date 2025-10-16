package com.example.carez.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carez.databinding.ItemFoodBinding
import com.example.carez.domain.model.Food
import com.example.carez.R

class FoodAdapter (
    var listFood:List<Food>,
    private val onItemClick: (Food) -> Unit
) :RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = listFood[position]
            holder.binding.txtNameFood.text = food.name
            holder.binding.txtCalo.text = "${food.gram}g · ${food.calo} kcal"
            holder.binding.txtLipid.text = "${food.lipid} g"
            holder.binding.txtFiber.text = "${food.fiber} g"
            holder.binding.txtProtein.text = "${food.protein} g"
            holder.binding.txtSugar.text = "${food.sugar} g"
            holder.binding.txtSalt.text = "${food.salt} g"
            val imageSource = food.localPath ?: food.remoteUrl
            if (!imageSource.isNullOrEmpty()) {
                Glide.with(holder.binding.imgFood.context)
                    .load(imageSource)
                    .placeholder(R.drawable.img_comrang)
                    .into(holder.binding.imgFood)
            } else {
                holder.binding.imgFood.setImageResource(R.drawable.img_comrang)
            }

        holder.binding.root.setOnClickListener {
            onItemClick(food)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    inner class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

}