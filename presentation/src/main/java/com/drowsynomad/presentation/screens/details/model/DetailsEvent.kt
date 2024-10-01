package com.drowsynomad.presentation.screens.details.model

import com.drowsynomad.presentation.core.common.UiEvent

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

sealed class DetailsEvent: UiEvent {
    data class LoadDetails(
        val id: String,
        val color: String,
        val imageUrl: String
    ): DetailsEvent()
}