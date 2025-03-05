package com.dieski.data.datasource.remote

import com.dieski.data.model.PlatformForceUpdateDto
import com.dieski.domain.model.platform.PlatformVersion
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface PlatformConfigRemoteDataSource {

	fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	): Flow<PlatformForceUpdateDto?>
}