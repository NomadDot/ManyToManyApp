package com.drowsynomad.manytomanyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.drowsynomad.manytomanyapp.navigationGraph.RootGraph
import com.drowsynomad.presentation.core.theme.ManyToManyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManyToManyAppTheme {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.Black)) {
                    RootGraph()
                }
            }
        }
    }
}