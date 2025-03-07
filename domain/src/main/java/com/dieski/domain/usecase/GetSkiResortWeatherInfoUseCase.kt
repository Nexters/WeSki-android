package com.dieski.domain.usecase

import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSkiResortWeatherInfoUseCase @Inject constructor(
    private val weSkiRepository: WeSkiRepository
) {
    operator fun invoke(resortId: Long): Flow<SkiResortWeatherInfo> =
        weSkiRepository.getSkiResortWeatherInfo(resortId)
}