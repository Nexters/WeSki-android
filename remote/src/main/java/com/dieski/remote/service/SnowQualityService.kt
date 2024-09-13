package com.dieski.remote.service

import com.dieski.remote.model.base.NetworkResult
import com.dieski.remote.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.remote.model.response.SnowQualitySurveyResultResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 * @author   JGeun
 * @created  2024/09/13
 */
interface SnowQualityService {

	@POST("/ski/{key}/snowmaking")
	suspend fun submitSnowQualitySurvey(
		@Path("key") resortId: Int,
		@Body request: SubmitSnowQualitySurveyRequest
	) : NetworkResult<Unit>

	@GET("/ski/{key}/snowmaking")
	suspend fun fetchingSnowQualitySurveyResult(
		@Path("key") resortId: Int
	) : NetworkResult<SnowQualitySurveyResultResponse>
}