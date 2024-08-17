package com.dieski.data.remote.network.service

import com.dieski.data.remote.network.model.response.ResortWeatherInfoResponse
import retrofit2.http.GET

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
interface WeSkiService {

	@GET("/ski")
	fun fetchResortWeatherInfoList(): List<ResortWeatherInfoResponse>
}