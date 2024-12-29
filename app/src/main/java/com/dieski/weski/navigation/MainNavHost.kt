package com.dieski.weski.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dieski.weski.presentation.detail.navigation.detailNavGraph
import com.dieski.weski.presentation.detail.navigation.webcamConnectNavGraph
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
                navigateToDetail = {
					navigator.navigateToDetail(it.id)
				},
                onShowSnackBar = onShowSnackBar,
            )

            detailNavGraph(
                padding = padding,
                onNavigateUp = navigator::popBackStackIfNotHome,
                navigateToWebcamConnect = { resortId, resortName ->
                    navigator.navigateToWebcamConnect(
                        resortId = resortId,
                        resortName = resortName
                    )
                },
                onShowSnackBar = onShowSnackBar,
            )

            webcamConnectNavGraph(
                padding = padding,
                onNavigateUp = navigator::popBackStackIfNotHome,
            )
        }
    }
}