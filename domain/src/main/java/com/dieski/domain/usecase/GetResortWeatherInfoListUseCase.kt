package com.dieski.domain.usecase

import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject
import javax.inject.Named

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
class GetResortWeatherInfoListUseCase @Inject constructor(
	private val weSkiRepository: WeSkiRepository
) {
	suspend operator fun invoke() = weSkiRepository.fetchAllSkiResortsInfo()
}