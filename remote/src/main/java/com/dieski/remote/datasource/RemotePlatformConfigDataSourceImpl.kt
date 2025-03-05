package com.dieski.remote.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.datasource.remote.PlatformConfigRemoteDataSource
import com.dieski.data.model.PlatformForceUpdateDto
import com.dieski.domain.model.platform.PlatformType
import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.domain.network.NetworkResult
import com.dieski.remote.service.WeSkiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
class RemotePlatformConfigDataSourceImpl @Inject constructor(
	private val weSkiService: WeSkiService,
	private val logger: AnalyticsLogger,
) : PlatformConfigRemoteDataSource {

	override fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	): Flow<PlatformForceUpdateDto?> = flow {
		when (val result = weSkiService.getPlatformForceUpdateStatus(platformVersion.value)) {
			is NetworkResult.Success -> {
				val responsePlatformType = PlatformType.findByValue(result.data.platform)
				if (responsePlatformType != PlatformType.ANDROID) {
					logger.logError(
						IllegalStateException("Platform is not collect in Server Response $responsePlatformType"),
						"DefaultRemotePlatformConfigDataSource - checkPlatformForceUpdateMinAppVersion()에서 발생"
					)
					emit(null)
				}
				emit(result.data.toData())
			}

			is NetworkResult.Failure -> {
				logger.logError(result.throwable, "DefaultRemotePlatformConfigDataSource - checkPlatformForceUpdateMinAppVersion()에서 발생")
				emit(null)
			}
		}
	}
}