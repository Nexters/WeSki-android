package com.dieski.weski.presentation.weather.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dieski.weski.presentation.core.navigation.MainTabRoute
import com.dieski.weski.presentation.weather.WeatherRouter

fun NavController.navigateWeather(navOptions: NavOptions) {
    navigate(MainTabRoute.Weather, navOptions)
}

fun NavGraphBuilder.weatherNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<MainTabRoute.Weather> {
        WeatherRouter(
            padding = padding,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}