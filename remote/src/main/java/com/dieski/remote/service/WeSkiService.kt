package com.dieski.remote.service

import com.dieski.remote.model.response.BriefResortInfoResponse
import com.dieski.remote.model.response.ResortWeatherInfoResponse
import retrofit2.http.GET
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
}