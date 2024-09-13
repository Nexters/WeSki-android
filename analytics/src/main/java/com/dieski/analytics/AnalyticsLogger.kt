package com.dieski.analytics

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface AnalyticsLogger {
	fun logEvent(event: AnalyticsEvent)

	fun logError(
		throwable: Throwable,
		message: String? = null
	)

	companion object {
		val stub =
			object :AnalyticsLogger {
				override fun logEvent(event: AnalyticsEvent) = Unit

				override fun logError(throwable: Throwable, message: String?) =Unit
			}
	}
}