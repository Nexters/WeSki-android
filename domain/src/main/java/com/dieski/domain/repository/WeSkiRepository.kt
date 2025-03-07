package com.dieski.domain.repository

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	fun getSkiResortList() : Flow<List<SkiResortInfo>>

	fun getSkiResortWeatherInfo(resortId: Long) : Flow<SkiResortWeatherInfo>

	suspend fun saveResortBookmark(resortId: Long): Result<Unit>

	suspend fun deleteResortBookmark(resortId: Long): Result<Unit>
}