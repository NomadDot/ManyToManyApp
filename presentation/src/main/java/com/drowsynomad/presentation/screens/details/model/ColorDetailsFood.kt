package com.drowsynomad.presentation.screens.details.model

import androidx.compose.ui.graphics.Color

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

data class ColorDetailsFood(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val color: Color = Color.White
)
