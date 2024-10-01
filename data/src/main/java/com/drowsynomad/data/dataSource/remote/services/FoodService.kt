package com.drowsynomad.data.dataSource.remote.services

import com.drowsynomad.data.dataSource.remote.response.DetailedFoodResponse
import com.drowsynomad.data.dataSource.remote.response.RandomFoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

interface FoodService {
    @GET(value = "texts/{itemId}")
    suspend fun fetchFoodDetails(
        @Path("itemId") itemId: String
    ): Response<DetailedFoodResponse>

    @GET(value = "items/random")
    suspend fun fetchFood(): Response<RandomFoodResponse>
}