package com.drowsynomad.data.repository

import com.drowsynomad.data.dataSource.remote.models.NetworkResponse
import com.drowsynomad.data.dataSource.remote.services.FoodService
import com.drowsynomad.data.dataSource.remote.wrapApi
import com.drowsynomad.data.mapper.FoodResponseMapper
import com.drowsynomad.domain.models.Food
import com.drowsynomad.domain.models.FoodDetails
import com.drowsynomad.domain.repository.IFoodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

class FoodRepository(
    private val service: FoodService,
    private val dispatcherPool: CoroutineDispatcher = Dispatchers.IO,
    private val mapper: FoodResponseMapper
) : IFoodRepository {

    override suspend fun fetchFood(): Food =
        withContext(dispatcherPool) {
            val networkResponse = wrapApi { service.fetchFood() }

            when (networkResponse) {
                is NetworkResponse.Error -> throw Throwable(networkResponse.message)
                is NetworkResponse.Success -> mapper.map(networkResponse.data)
            }
        }

    override suspend fun fetchFoodDetails(id: String): FoodDetails =
        withContext(dispatcherPool) {
            val networkResponse = wrapApi { service.fetchFoodDetails(id) }

            when (networkResponse) {
                is NetworkResponse.Error -> throw Throwable(networkResponse.message)
                is NetworkResponse.Success -> mapper.map(networkResponse.data)
            }
        }
}