package com.example.carez.presentation.activity.editfood

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carez.databinding.ActivityEditFoodBinding
import com.example.carez.domain.model.Food

class EditFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditFoodBinding
    private var currentFood: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu món ăn từ intent
        val food: Food? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("food", Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("food")
        }


        currentFood?.let {
            binding.apply {
                edtName.setText(it.name)
                edtCalo.setText(it.calo.toString())
                edtGram.setText(it.gram.toString())
                edtProtein.setText(it.protein.toString())
                edtLipid.setText(it.lipid.toString())
                edtFiber.setText(it.fiber.toString())
                edtSugar.setText(it.sugar.toString())
                edtSalt.setText(it.salt.toString())

                Glide.with(this@EditFoodActivity)
                    .load(it.remoteUrl ?: it.localPath)
                    .into(imgPreview)
            }
        }

        // Khi nhấn nút "Lưu"
        binding.btnSave.setOnClickListener {
            val updatedFood = currentFood?.copy(
                name = binding.edtName.text.toString(),
                calo = binding.edtCalo.text.toString().toIntOrNull() ?: 0,
                gram = binding.edtGram.text.toString().toIntOrNull() ?: 0,
                protein = binding.edtProtein.text.toString().toIntOrNull() ?: 0,
                lipid = binding.edtLipid.text.toString().toFloatOrNull() ?: 0f,
                fiber = binding.edtFiber.text.toString().toIntOrNull() ?: 0,
                sugar = binding.edtSugar.text.toString().toIntOrNull() ?: 0,
                salt = binding.edtSalt.text.toString().toIntOrNull() ?: 0
            )

            // TODO: Gọi ViewModel hoặc Repository để lưu món ăn vào DB / Firestore
            Toast.makeText(this, "Đã cập nhật món: ${updatedFood?.name}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
