package com.drowsynomad.manytomanyapp.di

import com.drowsynomad.data.dataSource.remote.ApiClient
import com.drowsynomad.data.dataSource.remote.RestApiClient
import com.drowsynomad.data.dataSource.remote.services.FoodService
import com.drowsynomad.data.mapper.FoodMapper
import com.drowsynomad.data.mapper.FoodResponseMapper
import com.drowsynomad.data.repository.FoodRepository
import com.drowsynomad.domain.repository.IFoodRepository
import org.koin.dsl.module

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

internal val dataModule = module {
    single {
        RestApiClient.instance.init()
        RestApiClient.instance as ApiClient
    }

    single {
        get<ApiClient>().provideService(FoodService::class.java)
    }

    factory {
        FoodRepository(
            service = get(),
            mapper = get()
        ) as IFoodRepository
    }

    single { FoodMapper() as FoodResponseMapper }
}