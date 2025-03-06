package com.dieski.domain.repository

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	fun getSkiResortList() : Flow<List<SkiResortInfo>>

	suspend fun fetchSkiResortWeatherInfo(resortId: Long) : WResult<SkiResortWeatherInfo, WError>

	suspend fun saveResortBookmark(resortId: Long)

	suspend fun deleteResortBookmark(resortId: Long)
}