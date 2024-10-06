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
	resortName: String,
	resortWebKey: String,
	temperature: Int,
	weatherType: String,
	weatherDescription: String
) {
	navigate(
		Route.Detail(
			resortId, resortName, resortWebKey, temperature, weatherType, weatherDescription
		)
	)
}

fun NavGraphBuilder.detailNavGraph(
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (String, String?) -> Unit,
	) {
	composable<Route.Detail> { navBackStackEntry ->
		val resortId = navBackStackEntry.toRoute<Route.Detail>().resortId
		val resortName = navBackStackEntry.toRoute<Route.Detail>().resortName
		val resortWebKey = navBackStackEntry.toRoute<Route.Detail>().resortWebKey
		val temperature = navBackStackEntry.toRoute<Route.Detail>().temperature
		val weatherCondition = WeatherCondition.findByName(navBackStackEntry.toRoute<Route.Detail>().weatherType)
		val weatherDescription = navBackStackEntry.toRoute<Route.Detail>().weatherDescription

		DetailRouter(
			resortId = resortId,
			resortName = resortName,
			resortWebKey = resortWebKey,
			temperature = temperature,
			weatherCondition = weatherCondition,
			weatherDescription = weatherDescription,
			padding = padding,
			onNavigateUp = onNavigateUp,
			onShowSnackBar = onShowSnackBar
		)
	}
}