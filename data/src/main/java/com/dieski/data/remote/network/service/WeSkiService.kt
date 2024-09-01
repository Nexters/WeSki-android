package com.dieski.data.remote.network.service

import com.dieski.data.remote.network.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.data.remote.network.model.response.BriefResortInfoResponse
import com.dieski.data.remote.network.model.response.ResortWeatherInfoResponse
import com.dieski.data.remote.network.model.response.SnowQualitySurveyResultResponse
import com.dieski.domain.model.NetworkResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
interface WeSkiService {

	@GET("/ski")
	suspend fun fetchResortWeatherInfoList(): List<ResortWeatherInfoResponse>

	@GET("/ski/{key}")
	suspend fun fetchBriefResortInfo(
		@Path("key") resortId: Int
	): BriefResortInfoResponse

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