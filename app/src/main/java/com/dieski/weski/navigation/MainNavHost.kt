package com.dieski.weski.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.dieski.weski.presentation.home.navigation.homeNavGraph
import com.dieski.weski.presentation.weather.navigation.weatherNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                onShowErrorSnackBar = onShowErrorSnackBar
            )

            weatherNavGraph(
                padding = padding,
                onShowErrorSnackBar = onShowErrorSnackBar
            )
        }
    }
}