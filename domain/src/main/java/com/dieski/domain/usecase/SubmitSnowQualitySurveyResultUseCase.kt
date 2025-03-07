package com.dieski.domain.usecase

import com.dieski.domain.repository.SnowQualityRepository
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
		resortId: Long,
		isPositive:Boolean
	): Result<Unit> {
		// 에러 여부와 상관없이 데이터 전송
		return snowQualityRepository.submitSnowQualitySurvey(resortId, isPositive)
	}
}