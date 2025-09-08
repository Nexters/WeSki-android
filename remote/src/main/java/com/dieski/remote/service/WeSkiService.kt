package com.dieski.remote.service

import com.dieski.domain.model.platform.PlatformType
import com.dieski.remote.model.network.NetworkResult
import com.dieski.remote.model.response.PlatformForceUpdateResponse
import com.dieski.remote.model.response.SkiResortInfoResponse
import com.dieski.remote.model.response.ResortWeatherInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
interface WeSkiService {

	@GET("/api/ski-resorts")
	suspend fun getSkiResortList(): NetworkResult<List<SkiResortInfoResponse>>

	@GET("/api/weather/{resortId}")
	suspend fun getSkiResortWeatherInfo(
		@Path("resortId") resortId: Long
	): NetworkResult<ResortWeatherInfoResponse>

	@GET("/api/app-version")
	suspend fun getPlatformForceUpdateStatus(
		@Query("version") version: String,
		@Query("platform") platform: String = PlatformType.ANDROID.value,
	): NetworkResult<PlatformForceUpdateResponse>
}