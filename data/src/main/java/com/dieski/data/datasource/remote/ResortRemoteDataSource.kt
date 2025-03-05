package com.dieski.data.datasource.remote

import com.dieski.data.model.ResortInfoDto
import com.dieski.data.model.ResortWeatherInfoDto
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface ResortRemoteDataSource {

	suspend fun fetchAllSkiResortsInfo(): WResult<List<ResortInfoDto>, DataError>

	suspend fun fetchSkiResortWeatherInfo(
		resortId: Long
	): WResult<ResortWeatherInfoDto, DataError>
}