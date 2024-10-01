package com.drowsynomad.presentation.screens.main.model

import com.drowsynomad.presentation.core.common.UiState

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

data class MainScreenState(
    val isLoading: Boolean = true,
    val foods: FoodUI = FoodUI("", emptyList())
) : UiState