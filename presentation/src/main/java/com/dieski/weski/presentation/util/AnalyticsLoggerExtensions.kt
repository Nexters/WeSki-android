package com.dieski.weski.presentation.util

import androidx.navigation.NavDestination
import com.dieski.analytics.AnalyticsEvent
import com.dieski.analytics.AnalyticsLogger
import com.dieski.weski.presentation.core.navigation.Route.Detail
import com.dieski.weski.presentation.core.navigation.Route.Home
import com.dieski.weski.presentation.model.WeSkiEnterScreenLoggerRoute

/**
 *
 * @author   JGeun
 * @created  2024/11/22
 */
fun AnalyticsLogger.logClickEvent(eventName: String) {
	logEvent(
		AnalyticsEvent(
			type = AnalyticsEvent.Types.ACTION,
			extras = listOf(
				AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.ACTION_NAME, eventName)
			)
		)
	)
}

fun AnalyticsLogger.logClickEvent(actionName: String, eventName: String) {
	logEvent(
		AnalyticsEvent(
			type = AnalyticsEvent.Types.ACTION,
			extras = listOf(
				AnalyticsEvent.Param(actionName, eventName)
			)
		)
	)
}

fun AnalyticsLogger.logScreenName(eventName: String) {
	logEvent(
		AnalyticsEvent(
			type = AnalyticsEvent.Types.SCREEN_VIEW,
			extras = listOf(
				AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SCREEN_NAME, eventName)
			)
		)
	)
}

fun AnalyticsLogger.log(type: String, eventName: String, eventValue: String) {
	logEvent(
		AnalyticsEvent(
			type = type,
			extras = listOf(
				AnalyticsEvent.Param(eventName, eventValue)
			)
		)
	)
}

fun AnalyticsLogger.log(eventName: String, eventValue: String) {
	logEvent(
		AnalyticsEvent(
			type = eventName,
			extras = listOf(
				AnalyticsEvent.Param(eventName, eventValue)
			)
		)
	)
}