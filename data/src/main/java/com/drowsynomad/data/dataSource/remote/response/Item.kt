package com.drowsynomad.data.dataSource.remote.response

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("color")
    val color: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?
)