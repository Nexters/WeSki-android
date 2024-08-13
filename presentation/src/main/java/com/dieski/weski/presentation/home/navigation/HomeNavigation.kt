package com.dieski.weski.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dieski.weski.presentation.core.navigation.Route
import com.dieski.weski.presentation.home.HomeRouter
import com.dieski.weski.presentation.home.model.HomeResortWeatherInfo

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(Route.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToDetail: (HomeResortWeatherInfo) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Route.Home> {
        HomeRouter(
            padding = padding,
            navigateToDetail = navigateToDetail,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}