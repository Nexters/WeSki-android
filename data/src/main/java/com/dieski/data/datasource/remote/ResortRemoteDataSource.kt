package com.dieski.data.datasource.remote

import com.dieski.data.model.ResortInfoDto
import com.dieski.data.model.ResortWeatherInfoDto
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface ResortRemoteDataSource {

	fun getSkiResortList(): Flow<List<ResortInfoDto>>

	suspend fun fetchSkiResortWeatherInfo(
		resortId: Long
	): WResult<ResortWeatherInfoDto, DataError>
}