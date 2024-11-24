package com.dieski.weski.presentation.model

import androidx.navigation.NavDestination
import com.dieski.weski.presentation.core.navigation.Route.Detail
import com.dieski.weski.presentation.core.navigation.Route.Home
import com.dieski.weski.presentation.util.getWeSkiRouteName

/**
 *
 * @author   JGeun
 * @created  2024/11/24
 */
@JvmInline
value class WeSkiEnterScreenLoggerRoute(
	val value: String?
) {
	fun getEventName(): String? = when (value) {
		Home.toString() -> "home_view"
		Detail.toString() -> "page_view_details"
		else -> null
	}

	companion object {
		fun createWeSkiEnterScreenLoggerRoute(
			navDestination: NavDestination?
		): WeSkiEnterScreenLoggerRoute {
			return WeSkiEnterScreenLoggerRoute(navDestination?.getWeSkiRouteName())
		}
	}
}
