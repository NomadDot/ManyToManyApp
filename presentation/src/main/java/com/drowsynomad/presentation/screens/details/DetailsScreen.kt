package com.drowsynomad.presentation.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drowsynomad.presentation.core.base.StateContent
import com.drowsynomad.presentation.core.common.Action
import com.drowsynomad.presentation.core.components.AsyncImage
import com.drowsynomad.presentation.core.components.Loader
import com.drowsynomad.presentation.core.components.Toolbar
import com.drowsynomad.presentation.navigation.FoodDetailsNavModel
import com.drowsynomad.presentation.screens.details.model.DetailsEvent
import com.drowsynomad.presentation.screens.details.model.DetailsState

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    navigationModel: FoodDetailsNavModel,
    onBackNavigation: Action,
) {
    StateContent(
        viewModel = viewModel,
        launchedEffect = {
            viewModel.handleUiEvent(
                DetailsEvent.LoadDetails(
                    id = navigationModel.id,
                    color = navigationModel.rawColor,
                    imageUrl = navigationModel.imageUrl
                )
            )
        }
    ) {
        DetailsContent(state = it, onBackNavigation)
    }
}

@Composable
private fun DetailsContent(
    state: DetailsState,
    onBackNavigation: Action
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Toolbar(
            modifier = Modifier.fillMaxWidth(),
            text = state.toolbarTitle,
            useAnimation = false,
            onBackNavigation = { onBackNavigation.invoke() }
        )
        Box(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Loader(modifier = Modifier.align(Alignment.TopCenter))
            this@Column.AnimatedVisibility(
                visible = !state.isLoading,
                enter = fadeIn(tween(500)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(state.foodDetails.color, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        imageUrl = state.foodDetails.imageUrl,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(150.dp)
                    )
                    Text(
                        text = state.foodDetails.description,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}