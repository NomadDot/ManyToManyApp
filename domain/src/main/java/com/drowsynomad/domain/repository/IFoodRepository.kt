package com.drowsynomad.domain.repository

import com.drowsynomad.domain.models.Food
import com.drowsynomad.domain.models.FoodDetails

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

interface IFoodRepository {
    suspend fun fetchFood(): Food
    suspend fun fetchFoodDetails(id: String): FoodDetails
}