package com.dieski.analytics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
private const val DEBUG_MODE = "debug"
private const val RELEASE_MODE = "release"

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

	@Provides
	@Singleton
	fun providesAnalyticsLogger(): AnalyticsLogger {
		return when (BuildConfig.BUILD_TYPE) {
			DEBUG_MODE -> DebugAnalyticsLogger
			RELEASE_MODE -> FireBaseAnalyticsLogger
			else -> error("Unknown build type")
		}
	}
}