package com.drowsynomad.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import com.drowsynomad.presentation.core.common.Action

/**
 * @author Roman Voloshyn (Created on 01.10.2024)
 */

fun Modifier.onClick(action: Action): Modifier {
    return this
        .clickable(
            indication = null,
            interactionSource = null
        ) {
            action.invoke()
        }
}