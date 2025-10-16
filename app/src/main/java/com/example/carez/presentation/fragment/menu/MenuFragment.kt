package com.example.carez.presentation.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carez.databinding.FragmentMenuBinding
import com.example.carez.presentation.activity.food.MenuFoodViewModel
import com.example.carez.presentation.fragment.menu.MenuFoodAdapter
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MenuFoodViewModel by viewModel()
    private lateinit var menuFoodAdapter: MenuFoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTabListener()
        observeFoods()

        viewModel.getFoodsByCategory("Đồ ăn nhanh")
    }

    private fun setupRecyclerView() {
        menuFoodAdapter = MenuFoodAdapter { food ->
            Toast.makeText(requireContext(), "Chọn: ${food.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvFood.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFood.adapter = menuFoodAdapter

        viewModel.foods.observe(viewLifecycleOwner) { list ->
            menuFoodAdapter.submitList(list)
        }

    }

    private fun setupTabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> viewModel.getFoodsByCategory("Đồ ăn nhanh")
                    1 -> viewModel.getFoodsByCategory("Đồ ăn vặt")
                    2 -> viewModel.getFoodsByCategory("Món nấu chín")
                    3 -> viewModel.getFoodsByCategory("Món chay")
                    4 -> viewModel.getFoodsByCategory("Thực phẩm tươi sống")
                    5 -> viewModel.getFoodsByCategory("Đồ uống")
                    6 -> viewModel.getFoodsByCategory("Đã thêm")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun observeFoods() {
        viewModel.foods.observe(viewLifecycleOwner) { list ->
            menuFoodAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}