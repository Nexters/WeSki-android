package com.dieski.domain.usecase

import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
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
	): WResult<SnowQualitySurveyResult, WError> {
		// 에러 여부와 상관없이 데이터 전송
		snowQualityRepository.submitSnowQualitySurvey(resortId, isPositive)

		return snowQualityRepository.fetchSnowQualitySurveyResult(resortId)
	}
}