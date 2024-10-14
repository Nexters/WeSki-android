package com.dieski.domain.usecase

import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
class GetAllSkiResortsUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
) {
	suspend operator fun invoke() = weSkiRepository.fetchAllSkiResortsInfo()
}