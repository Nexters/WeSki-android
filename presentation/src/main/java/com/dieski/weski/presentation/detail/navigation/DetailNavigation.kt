package com.dieski.weski.presentation.detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dieski.weski.presentation.core.navigation.Route
import com.dieski.weski.presentation.detail.DetailRouter
import com.dieski.weski.presentation.detail.web.WebcamConnectRouter

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
	navigateToWebcamConnect: (Long, String) -> Unit,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (String, String?) -> Unit,
) {
	composable<Route.Detail> { navBackStackEntry ->
		val resortId = navBackStackEntry.toRoute<Route.Detail>().resortId

		DetailRouter(
			resortId = resortId,
			padding = padding,
			navigateToWebcamConnect = navigateToWebcamConnect,
			onNavigateUp = onNavigateUp,
			onShowSnackBar = onShowSnackBar,
		)
	}
}

fun NavController.navigateWebcamConnect(
	resortId: Long,
	resortName: String
) {
	navigate(
		Route.WebcamConnect(resortId, resortName)
	)
}

fun NavGraphBuilder.webcamConnectNavGraph(
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
) {
	composable<Route.WebcamConnect> { navBackStackEntry ->
		val webcamConnect = navBackStackEntry.toRoute<Route.WebcamConnect>()

		WebcamConnectRouter(
			resortId = webcamConnect.resortId,
			resortName = webcamConnect.resortName,
			padding = padding,
			onNavigateUp = onNavigateUp,
		)
	}
}