package com.drowsynomad.presentation.screens.details

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.drowsynomad.domain.models.FoodDetails
import com.drowsynomad.domain.repository.IFoodRepository
import com.drowsynomad.presentation.core.base.StateViewModel
import com.drowsynomad.presentation.core.theme.colorFromString
import com.drowsynomad.presentation.screens.details.model.ColorDetailsFood
import com.drowsynomad.presentation.screens.details.model.DetailsEvent
import com.drowsynomad.presentation.screens.details.model.DetailsState
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

class DetailsViewModel(
    private val repository: IFoodRepository
): StateViewModel<DetailsState, DetailsEvent>(
    initialState = DetailsState(isLoading = true)
) {
    override fun handleUiEvent(uiEvent: DetailsEvent) {
        when(uiEvent) {
            is DetailsEvent.LoadDetails -> loadDetails(
                uiEvent.id,
                colorFromString(uiEvent.color),
                uiEvent.imageUrl
            )
        }
    }

    private fun loadDetails(id: String, color: Color, imageUrl: String) =
        launch(
            exceptionHandler = CoroutineExceptionHandler { _, _ ->
                updateState {
                    it.copy(isLoading = true)
                }
            },
            block = {
                val data = repository.fetchFoodDetails(id)

                updateState {
                    it.copy(
                        isLoading = false,
                        toolbarTitle = data.name.capitalize(Locale.current),
                        foodDetails = data.toColoredDetails(color, imageUrl)
                    )
                }
            }
        )

    private fun FoodDetails.toColoredDetails(color: Color, imageUrl: String) =
        ColorDetailsFood(
            id = name,
            name = name,
            description = description,
            imageUrl = imageUrl,
            color = color
        )
}