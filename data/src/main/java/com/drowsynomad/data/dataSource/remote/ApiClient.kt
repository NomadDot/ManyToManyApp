package com.drowsynomad.data.dataSource.remote

import com.drowsynomad.data.dataSource.remote.models.NetworkResponse
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

interface ApiClient {
    fun init()
    fun <T> provideService(service: Class<T>): T
}

suspend fun <T : Any> wrapApi(
    execute: suspend () -> Response<T>,
): NetworkResponse<T> {
    return try {
        val response = execute()
        val body = response.body()

        when {
            response.isSuccessful && body != null ->
                NetworkResponse.Success(body)

            else ->
                NetworkResponse.Error(response.code())
        }
    } catch (e: HttpException) {
        NetworkResponse.Error(code = e.code(), message = e.message())
    }
}