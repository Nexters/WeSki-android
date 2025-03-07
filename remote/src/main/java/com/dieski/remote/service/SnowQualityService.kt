package com.dieski.remote.service

import com.dieski.remote.model.network.NetworkResult
import com.dieski.remote.model.response.TotalResortSnowSurveyResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author   JGeun
 * @created  2024/09/13
 */
interface SnowQualityService {

	@POST("/api/snow-maker/{resortId}/vote")
	suspend fun submitSnowQualitySurvey(
		@Path("resortId") resortId: Long,
		@Query("isPositive") isPositive: Boolean
	): NetworkResult<Unit>

	@GET("api/snow-maker/{resortId}")
	suspend fun getTotalResortSnowQualitySurvey(
		@Path("resortId") resortId: Long
	): NetworkResult<TotalResortSnowSurveyResponse>
}