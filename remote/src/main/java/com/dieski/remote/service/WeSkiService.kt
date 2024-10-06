package com.dieski.remote.service

import com.dieski.domain.network.NetworkResult
import com.dieski.remote.model.response.BriefResortInfoResponse
import com.dieski.remote.model.response.SkiResortInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
interface WeSkiService {

	@GET("/api/ski-resorts")
	suspend fun fetchAllSkiResortsInfo(): NetworkResult<List<SkiResortInfoResponse>>

	@GET("/ski/{key}")
	suspend fun fetchBriefResortInfo(
		@Path("key") resortId: Int
	): BriefResortInfoResponse
}