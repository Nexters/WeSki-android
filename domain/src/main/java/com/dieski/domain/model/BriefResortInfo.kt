package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
data class BriefResortInfo(
	val resortName: String,
	val operatingSlopeCount: Int,
	val currentTemperature: Int,
	val weatherDescription: String,
	val weatherType: String,
)