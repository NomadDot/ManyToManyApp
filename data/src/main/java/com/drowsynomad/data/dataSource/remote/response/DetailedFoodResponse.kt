package com.drowsynomad.data.dataSource.remote.response

import com.google.gson.annotations.SerializedName

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

data class DetailedFoodResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("text")
    val text: String? = null
)