package com.dieski.domain.usecase

import com.dieski.domain.repository.WeSkiRepository
import javax.inject.Inject

class GetSkiResortWeatherInfoUseCase @Inject constructor(
    private val weSkiRepository: WeSkiRepository
) {
    suspend operator fun invoke(resortId: Long) = weSkiRepository.fetchSkiResortWeatherInfo(resortId)
}