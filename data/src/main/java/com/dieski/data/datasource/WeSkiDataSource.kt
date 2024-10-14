package com.dieski.data.datasource

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.remote.model.response.SkiResortInfoResponse

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
interface WeSkiDataSource {

	suspend fun fetchAllSkiResortsInfo(): WResult<List<SkiResortInfo>, DataError>

	suspend fun fetchSkiResortWeatherInfo(
		resortId: Long
	): WResult<SkiResortWeatherInfo, DataError>
}