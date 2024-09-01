package com.dieski.domain.usecase

import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
class GetBriefResortInfoUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
) {
	suspend operator fun invoke(
		resortId: Int
	) = weSkiRepository.fetchBriefResortInfo(resortId)
}