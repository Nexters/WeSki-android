package com.dieski.weski.presentation.home.model

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortInfo.DailyWeather
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
	val status: String,
	val openingDate: String,
	val operatingSlopeCount: Int,
	val currentWeather: SkiResortInfo.CurrentWeather,
	val isBookmarked: Boolean = false,
	val weeklyWeather: ImmutableList<DailyWeather>,
) {
	fun getResortOperatingStatus(): String {
		return try {
			if (status == "운영중") {
				"운행중인 슬로프 ${operatingSlopeCount}개"
			} else {
				val (year, month, day) = openingDate.split("-").map { it.toInt() }
				"${month}월 ${day}일 개장 예정이에요"
			}
		} catch (e: Exception) {
			"예상치 못한 이슈가 발생했어요"
		}
	}
}

fun SkiResortInfo.toUiModel() = HomeSkiResortInfo(
	id = this.resortId,
	name = this.resortName,
	status = this.status,
	openingDate = this.openingDate,
	operatingSlopeCount = this.openSlopeCount,
	isBookmarked = this.isBookmarked,
	currentWeather = this.currentWeather,
	weeklyWeather = this.weeklyWeather.toPersistentList(),
)