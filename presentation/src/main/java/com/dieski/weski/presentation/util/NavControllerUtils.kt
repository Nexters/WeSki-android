package com.dieski.weski.presentation.util

import androidx.navigation.NavDestination

/**
 *
 * @author   JGeun
 * @created  2024/11/24
 */
fun NavDestination?.getWeSkiRouteName(): String? {
	val destinationRouteWithoutPath = this?.route
		?.split("/")
		?.getOrNull(0)
		?: return null

	return destinationRouteWithoutPath.split(".").lastOrNull()
}