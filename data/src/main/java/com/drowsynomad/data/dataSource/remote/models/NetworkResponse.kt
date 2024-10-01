package com.drowsynomad.data.dataSource.remote.models

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T): NetworkResponse<T>()
    data class Error<T>(val code: Int, val message: String = ""): NetworkResponse<T>()
}
