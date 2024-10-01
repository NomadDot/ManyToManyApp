package com.drowsynomad.domain.models

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

data class Food(
    val title: String,
    val details: List<Details>
) {
    data class Details(
        val id: String,
        val name: String,
        val image: String,
        val rawColor: String
    )
}
