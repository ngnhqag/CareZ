package com.example.carez.domain.usecase
import com.example.carez.domain.model.Food
import com.example.carez.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetFoodsByCategoryUseCase(
    private val foodRepository: FoodRepository
) {
    operator fun invoke(category: String): Flow<List<Food>> {
        return foodRepository.getFoodsByCategory(category)
    }
}

