package com.example.carez.presentation.fragment.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carez.R
import com.example.carez.databinding.ItemFoodBinding
import com.example.carez.domain.model.Food

class MenuFoodAdapter(
    private val onItemClick: (Food) -> Unit
) : RecyclerView.Adapter<MenuFoodAdapter.FoodViewHolder>() {

    private val listFood = mutableListOf<Food>()

    fun submitList(newList: List<Food>) {
        listFood.clear()
        listFood.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = listFood[position]
        with(holder.binding) {
            txtNameFood.text = food.name
            txtCalo.text = "${food.gram}g · ${food.calo} kcal"
            txtLipid.text = "${food.lipid} g"
            txtFiber.text = "${food.fiber} g"
            txtProtein.text = "${food.protein} g"
            txtSugar.text = "${food.sugar} g"
            txtSalt.text = "${food.salt} g"

            val context = imgFood.context
            val resId = context.resources.getIdentifier(food.localPath, "drawable", context.packageName)
            val imageSource = food.localPath ?: food.remoteUrl
            if (!imageSource.isNullOrEmpty()) {
                Glide.with(context)
                    .load(imageSource)
                    .placeholder(R.drawable.img_comrang)
                    .into(imgFood)
            } else {
                imgFood.setImageResource(R.drawable.img_comrang)
            }

            root.setOnClickListener { onItemClick(food) }
        }
    }

    override fun getItemCount(): Int = listFood.size

    inner class FoodViewHolder(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root)
}
