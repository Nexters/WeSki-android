package com.dieski.domain.usecase

import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
class SubmitSnowQualitySurveyResultUseCase @Inject constructor(
	private val snowQualityRepository: SnowQualityRepository
) {

	suspend operator fun invoke(
		resortId: Int,
		isLike:Boolean
	) = snowQualityRepository.submitSnowQualitySurvey(resortId, isLike)
}