package com.dieski.domain.usecase

import com.dieski.domain.repository.SnowQualityRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
class GetSnowQualitySurveyResultUseCase @Inject constructor(
	private val snowQualityRepository: SnowQualityRepository
) {
	suspend operator fun invoke(
		resortId: Long
	) = snowQualityRepository.fetchSnowQualitySurveyResult(resortId)
}