package com.drowsynomad.presentation.screens.details.model

import com.drowsynomad.presentation.core.common.UiState

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

data class DetailsState(
    val isLoading: Boolean = false,
    val toolbarTitle: String = "",
    val foodDetails: ColorDetailsFood = ColorDetailsFood()
): UiState