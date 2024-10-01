package com.drowsynomad.data.dataSource.remote.response

import com.google.gson.annotations.SerializedName

data class RandomFoodResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("items")
    val items: List<Item?>?,
)