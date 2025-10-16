package com.example.carez.domain.repository

import com.example.carez.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFoodsByCategory(category: String): Flow<List<Food>>
}
