package com.drowsynomad.data.mapper

import com.drowsynomad.data.dataSource.remote.response.DetailedFoodResponse
import com.drowsynomad.data.dataSource.remote.response.RandomFoodResponse
import com.drowsynomad.domain.models.Food
import com.drowsynomad.domain.models.FoodDetails

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

class FoodMapper: FoodResponseMapper {
    override fun map(input: RandomFoodResponse): Food {
        return Food(
            title = input.title ?: "",
            details = input.items
                ?.mapNotNull {
                    it?.let { item ->
                        Food.Details(
                            id = item.id ?: "",
                            name = item.name ?: "",
                            image = item.image ?: "",
                            rawColor = item.color ?: ""
                        )
                    }
                } ?: listOf()
        )
    }

    override fun map(input: DetailedFoodResponse): FoodDetails {
        return FoodDetails(
            name = input.id ?: "",
            description = input.text ?: ""
        )
    }
}