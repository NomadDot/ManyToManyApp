package com.drowsynomad.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.drowsynomad.presentation.core.base.StateContent
import com.drowsynomad.presentation.core.common.Action
import com.drowsynomad.presentation.core.components.AsyncImage
import com.drowsynomad.presentation.core.components.Loader
import com.drowsynomad.presentation.core.components.Toolbar
import com.drowsynomad.presentation.core.components.defaultExitAnim
import com.drowsynomad.presentation.core.components.foodCardEnter
import com.drowsynomad.presentation.core.components.longTween
import com.drowsynomad.presentation.core.theme.toRawColor
import com.drowsynomad.presentation.screens.main.model.FoodUI
import com.drowsynomad.presentation.screens.main.model.MainScreenEvent
import com.drowsynomad.presentation.screens.main.model.MainScreenState
import com.drowsynomad.presentation.utils.onClick
import kotlinx.coroutines.delay

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onFoodDetailsNavigation: (id: String, rawColor: String, imageUrl: String) -> Unit
) {
    StateContent(
        viewModel = viewModel,
        launchedEffect = {
            viewModel.handleUiEvent(MainScreenEvent.LoadFood)
        }
    ) {
        MainContent(
            state = it,
            onFoodDetailsNavigation = onFoodDetailsNavigation,
            onRefresh = { viewModel.handleUiEvent(MainScreenEvent.Refresh) }
        )
    }
}

@Composable
private fun MainContent(
    state: MainScreenState,
    onFoodDetailsNavigation: (id: String, rawColor: String, imageUrl: String) -> Unit,
    onRefresh: Action,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            text = state.foods.title,
            onRefreshClick = { onRefresh.invoke() }
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Loader(modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp))
            FoodColumn(foods = state.foods, onFoodDetailsNavigation = onFoodDetailsNavigation)
        }
    }
}

@Composable
private fun FoodColumn(
    modifier: Modifier = Modifier,
    foods: FoodUI,
    onFoodDetailsNavigation: (id: String, rawColor: String, imageUrl: String) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(foods.coloredFoods) {
            FoodItem(food = it, onFoodDetailsNavigation = onFoodDetailsNavigation)
        }
    }
}

@Composable
private fun FoodItem(
    modifier: Modifier = Modifier,
    food: FoodUI.ColoredFood,
    onFoodDetailsNavigation: (id: String, rawColor: String, imageUrl: String) -> Unit
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val paddingTop = animateDpAsState(
        targetValue = if(startAnimation) 16.dp else 2.dp,
        label = "animation_padding",
        animationSpec = longTween()
    )

    LaunchedEffect(key1 = Unit) {
        delay(50)
        startAnimation = true
    }

    AnimatedVisibility(
        visible = startAnimation,
        modifier = Modifier.padding(top = paddingTop.value),
        enter = foodCardEnter(LocalDensity.current),
        exit = defaultExitAnim()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(90.dp)
                .onClick {
                    onFoodDetailsNavigation.invoke(
                        food.id,
                        food.surfaceColor.toRawColor(),
                        food.imageUrl
                    )
                }
                .background(color = food.surfaceColor, shape = RoundedCornerShape(16.dp))
                .padding(start = 16.dp, end = 10.dp)
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = food.name,
                color = Color.White,
            )
            AsyncImage(
                imageUrl = food.imageUrl,
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
}