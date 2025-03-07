package com.dieski.domain.usecase

import com.dieski.domain.model.TotalResortSnowQualitySurvey
import com.dieski.domain.repository.SnowQualityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
class GetTotalResortSnowQualitySurveyUseCase @Inject constructor(
	private val snowQualityRepository: SnowQualityRepository
) {
	operator fun invoke(resortId: Long): Flow<TotalResortSnowQualitySurvey> =
		snowQualityRepository.getTotalResortSnowQualitySurvey(resortId)
}