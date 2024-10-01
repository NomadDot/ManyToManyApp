package com.drowsynomad.presentation.core.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drowsynomad.presentation.core.common.UiEvent
import com.drowsynomad.presentation.core.common.UiState

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

@Composable
fun <S: UiState, E: UiEvent> StateContent(
    viewModel: StateViewModel<S, E>,
    launchedEffect: (() -> Unit)? = null,
    content: @Composable BoxScope.(uiState: S) -> Unit
) {
    launchedEffect?.let { effect ->
        LaunchedEffect(key1 = Unit) {
            effect.invoke()
        }
    }

    val uiState by viewModel.publicState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        content(uiState)
    }
}