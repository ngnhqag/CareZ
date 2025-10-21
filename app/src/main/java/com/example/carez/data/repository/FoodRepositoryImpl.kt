package com.example.carez.data.repository

import com.example.carez.domain.model.Food
import com.example.carez.data.FoodAssetDataSource
import com.example.carez.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(
    private val assets: FoodAssetDataSource
) : FoodRepository {

    override fun getFoodsByCategory(category: String): Flow<List<Food>> = flow {
        val allFoods = assets.loadFoodsFromAssets()
        emit(allFoods.filter { it.category == category })
    }
}
