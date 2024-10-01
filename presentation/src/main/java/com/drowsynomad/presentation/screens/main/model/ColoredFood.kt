package com.drowsynomad.presentation.screens.main.model

import androidx.compose.ui.graphics.Color

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

data class FoodUI(
    val title: String,
    val coloredFoods: List<ColoredFood>
) {
    data class ColoredFood(
        val id: String,
        val name: String,
        val imageUrl: String,
        val surfaceColor: Color
    )
}
