package com.drowsynomad.presentation.screens.main.model

import com.drowsynomad.presentation.core.common.UiEvent

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

sealed class MainScreenEvent: UiEvent {
    data object LoadFood: MainScreenEvent()
    data object Refresh: MainScreenEvent()
}