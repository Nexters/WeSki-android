package com.dieski.weski.presentation.home.model

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.SkiResortWebKey
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import javax.annotation.concurrent.Immutable

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
@Immutable
data class HomeSkiResortInfo(
	val id: Long,
	val name: String,
	val webKey: SkiResortWebKey,
	val status: String,
	val operatingSlopeCount: Int,
	val currentWeather: SkiResortInfo.CurrentWeather,
	val weeklyWeather: ImmutableList<DailyWeather>,
)

fun SkiResortInfo.toUiModel() = HomeSkiResortInfo(
	id = this.resortId,
	name = this.resortName,
	webKey = this.resortWebKey,
	status = this.status,
	operatingSlopeCount = this.openSlopeCount,
	currentWeather = this.currentWeather,
	weeklyWeather = this.weeklyWeather.toPersistentList(),
)