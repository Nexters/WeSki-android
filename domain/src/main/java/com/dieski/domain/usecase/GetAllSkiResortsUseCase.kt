package com.dieski.domain.usecase

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
class GetAllSkiResortsUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
) {
	suspend operator fun invoke(): WResult<List<SkiResortInfo>, WError> =
		weSkiRepository.fetchAllSkiResortsInfo()
}