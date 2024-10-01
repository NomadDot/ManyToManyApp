package com.drowsynomad.manytomanyapp.navigationGraph

import kotlinx.serialization.Serializable

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

@Serializable
sealed class Routes {

    @Serializable
    data object MainScreen: Routes()

    @Serializable
    data class DetailsScreen(
        val foodId: String,
        val rawColor: String,
        val imageUrl: String
    ): Routes()
}