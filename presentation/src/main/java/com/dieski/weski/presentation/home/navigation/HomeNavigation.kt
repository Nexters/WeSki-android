package com.dieski.weski.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dieski.weski.presentation.home.HomeRouter
import com.dieski.weski.presentation.core.navigation.MainTabRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.HOME, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<MainTabRoute.HOME> {
        HomeRouter(
            padding = padding,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}