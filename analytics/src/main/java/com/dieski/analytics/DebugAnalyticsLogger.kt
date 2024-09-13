package com.dieski.analytics

import timber.log.Timber

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
internal object DebugAnalyticsLogger : AnalyticsLogger {
	override fun logEvent(event: AnalyticsEvent) {
		Timber.d("Event: $event")
	}

	override fun logError(throwable: Throwable, message: String?) {
		Timber.e(throwable, message)
	}
}