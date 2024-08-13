package com.dieski.domain.repository

import com.dieski.domain.model.ResortWeatherInfo
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	fun fetchResortWeatherInfoList() : Flow<List<ResortWeatherInfo>>
}