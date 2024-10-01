package com.drowsynomad.manytomanyapp.navigationGraph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.drowsynomad.presentation.navigation.FoodDetailsNavModel
import com.drowsynomad.presentation.screens.details.DetailsScreen
import com.drowsynomad.presentation.screens.main.MainScreen
import org.koin.androidx.compose.koinViewModel

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

@Composable
fun RootGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen
    ) {
        composable<Routes.MainScreen>(
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(500, easing = LinearEasing),
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    targetOffset = { -it/2 }
                ) + fadeOut(tween(500), targetAlpha = 0.7f)
            },
            popEnterTransition = {
                slideIntoContainer(
                    animationSpec = tween(500, easing = LinearEasing),
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    initialOffset = { it / 2 }
                ) + fadeIn(tween(500), initialAlpha = 0.7f)
            },
        ) {
            MainScreen(
                viewModel = koinViewModel(),
                onFoodDetailsNavigation = { id, color, imageUrl ->
                    navController.navigate(Routes.DetailsScreen(id, color, imageUrl))
                }
            )
        }

        composable<Routes.DetailsScreen>(
            enterTransition = {
               slideIntoContainer(
                    animationSpec = tween(500, easing = LinearEasing),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    initialOffset = { it }
                )
            },
            exitTransition = {
               slideOutOfContainer(
                    animationSpec = tween(500, easing = LinearEasing),
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
//                    targetOffset = { it }
                )
            }
        ) {
            val args = it.toRoute<Routes.DetailsScreen>()
            DetailsScreen(
                viewModel = koinViewModel(),
                navigationModel = FoodDetailsNavModel(
                    id = args.foodId,
                    rawColor = args.rawColor,
                    imageUrl = args.imageUrl
                ),
                onBackNavigation = {
                    navController.popBackStack()
                },
            )
        }
    }
}