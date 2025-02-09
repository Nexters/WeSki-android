package com.dieski.data.datasource

import android.util.Log
import com.dieski.analytics.AnalyticsLogger
import com.dieski.domain.model.platform.PlatformType
import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.domain.network.NetworkResult
import com.dieski.remote.model.response.PlatformForceUpdateCheckResult
import com.dieski.remote.service.WeSkiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
class DefaultRemotePlatformConfigDataSource @Inject constructor(
	private val weSkiService: WeSkiService,
	private val logger: AnalyticsLogger,
) : RemotePlatformConfigDataSource {

	override fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	): Flow<PlatformForceUpdateCheckResult?> = flow {
		Log.w("Test@@@", "platformVersion: ${platformVersion.value}")
		when (val result = weSkiService.checkPlatformVersionForForcedUpdate(platformVersion.value)) {
			is NetworkResult.Success -> {
				val responsePlatformType = PlatformType.findByValue(result.data.platform)
				if (responsePlatformType != PlatformType.ANDROID) {
					logger.logError(
						IllegalStateException("Platform is not collect in Server Response $responsePlatformType"),
						"DefaultRemotePlatformConfigDataSource - checkPlatformForceUpdateMinAppVersion()에서 발생"
					)
					emit(null)
				}
				emit(result.data)
			}
			is NetworkResult.Failure -> {
				logger.logError(result.throwable, "DefaultRemotePlatformConfigDataSource - checkPlatformForceUpdateMinAppVersion()에서 발생")
				emit(null)
			}
		}
	}
}