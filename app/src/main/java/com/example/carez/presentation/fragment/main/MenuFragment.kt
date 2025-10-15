package com.example.carez.presentation.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carez.databinding.FragmentMenuBinding
import com.example.carez.domain.model.Food
import com.example.carez.presentation.adapter.FoodAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listFood = mutableListOf<Food>(Food("1", "Cơm rang", 250, 500, 10.5f, 2, 15, 3, 1, 100),
            Food("2", "Phở bò", 350, 420, 8.0f, 1, 20, 2, 2, 150),
            Food("3", "Bánh mì trứng", 180, 310, 9.5f, 3, 12, 4, 1, 80),
            Food("4", "Bún chả", 300, 480, 15.0f, 2, 18, 3, 2, 100),
            Food("5", "Gà luộc", 200, 330, 7.5f, 0, 25, 0, 1, 120),
            Food("6", "Canh rau ngót", 250, 90, 2.0f, 3, 4, 1, 1, 200),
            Food("7", "Cá kho tộ", 220, 350, 12.0f, 0, 22, 2, 2, 100),
            Food("8", "Thịt kho trứng", 280, 520, 18.0f, 0, 20, 5, 2, 100),
            Food("9", "Bánh cuốn", 200, 250, 6.0f, 2, 10, 3, 1, 90),
            Food("10", "Sữa chua", 100, 120, 3.5f, 0, 6, 15, 0, 80))

        val adapter = FoodAdapter(listFood)
        binding.rvFood.adapter = adapter
        binding.rvFood.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false

        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}