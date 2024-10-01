package com.drowsynomad.presentation.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

private const val ImageBase = "https://test-task-server.mediolanum.f17y.com"

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Inside,
        alignment = Alignment.Center,
        clipToBounds = false,
        model = "$ImageBase$imageUrl",
        contentDescription = "async_food_image"
    )
}