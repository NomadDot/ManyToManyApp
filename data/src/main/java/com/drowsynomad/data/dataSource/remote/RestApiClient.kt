package com.drowsynomad.data.dataSource.remote

import com.drowsynomad.data.Constants
import com.drowsynomad.data.Constants.ApiClientErrorMessage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

class RestApiClient: ApiClient {
    companion object {
        val instance: ApiClient = RestApiClient()
    }

    private var retrofit: Retrofit? = null
    private val apiServices = HashMap<Class<*>, Any?>()

    override fun init() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> createService(service: Class<T>): T {
        if (retrofit == null)
            throw ExceptionInInitializerError(ApiClientErrorMessage)
        return if (apiServices.containsKey(service))
            apiServices[service] as T
        else {
            val apiService = retrofit?.create(service)
            apiServices[service] = apiService
            apiService as T
        }
    }

    override fun <T> provideService(service: Class<T>): T {
        return createService(service)
    }
}