package com.dieski.data.datasource

import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.remote.model.response.PlatformForceUpdateCheckResult
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
interface RemotePlatformConfigDataSource {

	fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	) : Flow<PlatformForceUpdateCheckResult?>
}