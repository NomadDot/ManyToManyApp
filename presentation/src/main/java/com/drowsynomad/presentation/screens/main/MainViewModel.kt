package com.drowsynomad.presentation.screens.main

import android.util.Log
import com.drowsynomad.domain.models.Food
import com.drowsynomad.domain.repository.IFoodRepository
import com.drowsynomad.presentation.core.base.StateViewModel
import com.drowsynomad.presentation.core.theme.colorFromString
import com.drowsynomad.presentation.screens.main.model.FoodUI
import com.drowsynomad.presentation.screens.main.model.FoodUI.ColoredFood
import com.drowsynomad.presentation.screens.main.model.MainScreenEvent
import com.drowsynomad.presentation.screens.main.model.MainScreenState
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

class MainViewModel(
    private val repository: IFoodRepository,
): StateViewModel<MainScreenState, MainScreenEvent>(
    initialState = MainScreenState(isLoading = true)
) {
    override fun handleUiEvent(uiEvent: MainScreenEvent) {
        when(uiEvent) {
            MainScreenEvent.LoadFood -> loadFood()
            MainScreenEvent.Refresh -> loadFood(isRefresh = true)
        }
    }

    private fun loadFood(isRefresh: Boolean = false) =
        launch(
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                Log.i(this::class.simpleName, "${throwable.message}")
                updateState { MainScreenState(isLoading = true) }
            },
            block = {
                if(isRefresh)
                    updateState { MainScreenState(isLoading = true) }

                val food = repository
                    .fetchFood()
                    .toColoredFood()

                updateState {
                    MainScreenState(isLoading = false, foods = food)
                }
            }
        )

    private fun Food.toColoredFood(): FoodUI =
        with(this) {
            FoodUI(
                title = title,
                coloredFoods = details.map {
                    ColoredFood(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.image,
                        surfaceColor = colorFromString(it.rawColor)
                    )
                }
            )
        }
}