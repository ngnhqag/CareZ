package com.example.carez.presentation.activity.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carez.domain.model.Food
import com.example.carez.domain.usecase.GetFoodsByCategoryUseCase
import kotlinx.coroutines.launch

class MenuFoodViewModel(
    private val getFoodsByCategoryUseCase: GetFoodsByCategoryUseCase
) : ViewModel() {

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    fun getFoodsByCategory(category: String) {
        viewModelScope.launch {
            getFoodsByCategoryUseCase(category).collect { result ->
                _foods.value = result
            }
        }
    }

}
