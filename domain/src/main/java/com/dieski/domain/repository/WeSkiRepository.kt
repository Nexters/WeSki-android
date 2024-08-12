package com.dieski.domain.repository

import com.dieski.domain.model.Resort

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	fun fetchResortWeatherInfoList() : List<Resort>
}