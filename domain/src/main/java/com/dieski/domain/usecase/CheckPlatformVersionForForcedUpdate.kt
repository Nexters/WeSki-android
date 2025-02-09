package com.dieski.domain.usecase

import com.dieski.domain.model.platform.PlatformType
import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.domain.repository.PlatformConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/02/10
 */
class CheckPlatformVersionForForcedUpdate @Inject constructor(
	private val platformConfigRepository: PlatformConfigRepository
) {
	operator fun invoke(
		platformType: PlatformType,
		platformVersion: PlatformVersion
	): Flow<Boolean> =
		platformConfigRepository.checkPlatformVersionForForcedUpdate(platformVersion).map {
			it != null && it.checkForceUpdate && it.platformType == platformType
		}
}