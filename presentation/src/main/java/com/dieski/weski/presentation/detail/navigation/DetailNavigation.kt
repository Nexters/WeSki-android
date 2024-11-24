package com.dieski.weski.presentation.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.navigation.Route
import com.dieski.weski.presentation.detail.DetailRouter

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
fun NavController.navigateDetail(
	resortId: Long,
) {
	navigate(
		Route.Detail(resortId)
	)
}

fun NavGraphBuilder.detailNavGraph(
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (String, String?) -> Unit,
) {
	composable<Route.Detail> { navBackStackEntry ->
		val resortId = navBackStackEntry.toRoute<Route.Detail>().resortId

		DetailRouter(
			resortId = resortId,
			padding = padding,
			onNavigateUp = onNavigateUp,
			onShowSnackBar = onShowSnackBar,
		)
	}
}