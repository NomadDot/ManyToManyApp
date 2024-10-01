package com.drowsynomad.presentation.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drowsynomad.presentation.R
import com.drowsynomad.presentation.core.common.Action
import com.drowsynomad.presentation.core.theme.Accent
import com.drowsynomad.presentation.utils.onClick

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    text: String,
    useAnimation: Boolean = true,
    onRefreshClick: Action? = null,
    onBackNavigation: Action? = null
) {
    @Composable
    fun Title(
        modifier: Modifier = Modifier,
        text: String
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            modifier = modifier
        )
    }

    Box(modifier = modifier
        .height(60.dp)
        .background(Accent)
        .padding(horizontal = 16.dp)
    ) {
        onBackNavigation?.let {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .onClick { it.invoke() },
            )
        }
        if(useAnimation)
            AnimatedVisibility(
                visible = text.isNotEmpty(),
                modifier = Modifier.align(Alignment.Center),
                enter = toolbarEnterAnim(),
                exit = defaultExitAnim()
            ) {
                Title(text = text)
            }
        else
            Title(
                text = text,
                modifier = Modifier.align(Alignment.Center)
            )

        onRefreshClick?.let {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .onClick { it.invoke() },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ToolbarPreview() {
    Toolbar(Modifier.fillMaxWidth(), text = "Tomato")
}
