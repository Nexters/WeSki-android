package com.dieski.data.repository.mapper

import com.dieski.remote.model.response.BriefResortInfoResponse
import com.dieski.domain.model.BriefResortInfo

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
fun BriefResortInfoResponse.toDomain() = BriefResortInfo(
	resortName = this.resortName,
	operatingSlopeCount = this.operationSlopeCount,
	currentTemperature = this.weather.temperature,
	weatherDescription = this.weather.description,
	weatherType = ""
)