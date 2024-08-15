package com.dieski.weski.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.dieski.weski.presentation.detail.navigation.detailNavGraph
import com.dieski.weski.presentation.home.navigation.homeNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowSnackBar: (String, String?) -> Unit,
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
                navigateToDetail = { navigator.navigateToDetail(it.name) },
                onShowSnackBar = onShowSnackBar
            )

            detailNavGraph(
                padding = padding,
                onNavigateUp = navigator::popBackStackIfNotHome,
                onShowSnackBar = onShowSnackBar
            )
        }
    }
}