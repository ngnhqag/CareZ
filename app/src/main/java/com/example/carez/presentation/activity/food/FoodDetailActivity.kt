package com.example.carez.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carez.databinding.ActivityFoodDetailBinding
import com.example.carez.domain.model.Food

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val food: Food? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("food", Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("food")
        }


        // Gán dữ liệu vào UI
        food?.let {
            binding.apply {
                txtName.text = it.name
                txtCal.text = "${it.calo} kcal"
                txtCalGram.text = "${it.calo} kcal / ${it.gram} g"
                txtProtein.text = "Protein: ${it.protein} g"
                txtFiber.text = "Chất xơ: ${it.fiber} g"
                txtLipid.text = "Lipid: ${it.lipid} g"
                txtSugar.text = "Đường: ${it.sugar} g"
                txtSalt.text = "Muối: ${it.salt} g"

                Glide.with(this@FoodDetailActivity)
                    .load(it.remoteUrl ?: it.localPath)
                    .into(imgFood)
            }
        }

        // Nút sửa món
        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditFoodActivity::class.java)
            intent.putExtra("food", food)
            startActivity(intent)
        }

        // Nút xóa món
        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Xóa món ăn")
                .setMessage("Bạn có chắc muốn xóa món '${food?.name}' không?")
                .setPositiveButton("Xóa") { _, _ ->
                    // TODO: Gọi ViewModel hoặc Repository để xóa khỏi DB/Firestore
                    Toast.makeText(this, "Đã xóa ${food?.name}", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Hủy", null)
                .show()
        }
    }
}
