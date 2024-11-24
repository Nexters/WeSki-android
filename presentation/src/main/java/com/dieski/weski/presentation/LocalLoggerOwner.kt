package com.dieski.weski.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.dieski.analytics.AnalyticsLogger

/**
 *
 * @author   JGeun
 * @created  2024/11/23
 */
object LocalLoggerOwner {
	private val LocalAnalyticsLogger = staticCompositionLocalOf<AnalyticsLogger> {
		error("CompositionLocal LocalActivity not present")
	}

	val current: AnalyticsLogger
		@Composable
		get() = LocalAnalyticsLogger.current

	infix fun  provides(registerLogger: AnalyticsLogger): ProvidedValue<AnalyticsLogger> =
		LocalAnalyticsLogger provides registerLogger
}