package com.drowsynomad.data.mapper

import com.drowsynomad.data.dataSource.remote.response.DetailedFoodResponse
import com.drowsynomad.data.dataSource.remote.response.RandomFoodResponse
import com.drowsynomad.domain.models.Food
import com.drowsynomad.domain.models.FoodDetails

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

interface FoodResponseMapper {
    fun map(input: RandomFoodResponse): Food
    fun map(input: DetailedFoodResponse): FoodDetails
}