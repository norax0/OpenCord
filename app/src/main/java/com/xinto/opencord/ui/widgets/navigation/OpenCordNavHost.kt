package com.xinto.opencord.ui.widgets.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OpenCordNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    builder: NavGraphBuilder.() -> Unit
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        contentAlignment = contentAlignment,
        builder = builder,
        enterTransition = { _, _ ->
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Start
            ) + fadeIn()
        },
        exitTransition = { _, _ ->
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.End
            ) + fadeOut()
        },
        popEnterTransition = { _, _ ->
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.End
            ) + fadeIn()
        },
        popExitTransition = { _, _ ->
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Start
             ) + fadeOut()
        }
    )
}