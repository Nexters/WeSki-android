package com.dieski.weski.presentation.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dieski.weski.presentation.core.navigation.Route
import com.dieski.weski.presentation.detail.DetailRouter

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
fun NavController.navigateDetail(skiResortName: String) {
	navigate(Route.Detail(skiResortName))
}

fun NavGraphBuilder.detailNavGraph(
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (String, String?) -> Unit,
	) {
	composable<Route.Detail> { navBackStackEntry ->
		val skiResortName = navBackStackEntry.toRoute<Route.Detail>().skiResortName

		DetailRouter(
			skiResortName = skiResortName,
			padding = padding,
			onNavigateUp = onNavigateUp,
			onShowSnackBar = onShowSnackBar
		)
	}
}