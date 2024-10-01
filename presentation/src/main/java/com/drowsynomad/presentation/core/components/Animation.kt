package com.drowsynomad.presentation.core.components

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.unit.Density

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

fun toolbarEnterAnim(): EnterTransition =
    fadeIn(tween(800), initialAlpha = 0f)

fun defaultExitAnim(): ExitTransition =
    fadeOut(tween(0))

fun foodCardEnter(density: Density) =
   fadeIn(longTween(),)


fun <T> longTween() = tween<T>(1000)
