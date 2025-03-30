package com.dieski.weski.presentation.detail

import androidx.compose.runtime.Stable
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.model.TotalResortSnowQualitySurvey
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WebMobileData.Companion.SLOPE_PARAM
import com.dieski.domain.model.WebMobileData.Companion.WEBCAM_PARAM
import com.dieski.domain.model.WebMobileData.Companion.WEB_MOBILE_URL
import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

sealed interface DetailEvent : UiEvent {
	data class Init(
		val resortId: Long
	) : DetailEvent

	data object ClickBackButton : DetailEvent

	data object ClickShareButton : DetailEvent

	data class ToggleBookmark(
		val resortId: Long,
		val isBookmarked: Boolean
	) : DetailEvent

	data class ShowSnackBar(
		val message: String,
		val action: String?
	) : DetailEvent

	data class SubmitSnowQualitySurvey(
		val isPositive: Boolean
	) : DetailEvent

	data class ClickWebcam(
		val webViewUrl: String
	) : DetailEvent
}

@Stable
data class DetailState(
	val resortId: Long = -1L,
	val resortName: String = "",
	val openSlopes: Int = 0,
	val status: String = "",
	val openingDate: String = "0000-00-00",
	val isBookmarked: Boolean = false,
	val temperature: Int = 0,
	val weatherCondition: WeatherCondition = WeatherCondition.SUNNY,
	val weatherDescription: String = "",
	val todayWeatherByTime: ImmutableList<SkiResortWeatherInfo.HourlyWeather> = persistentListOf(),
	val weeklyWeather: ImmutableList<SkiResortWeatherInfo.DailyWeather> = persistentListOf(),
	val totalResortSnowQualitySurvey: TotalResortSnowQualitySurvey = TotalResortSnowQualitySurvey.EMPTY
) : UiState {
	val webcamWebUrl get() = "${WEB_MOBILE_URL}${WEBCAM_PARAM}${resortId}"
	val slopeWebUrl get() = "${WEB_MOBILE_URL}${SLOPE_PARAM}${resortId}"

	fun getResortOperatingStatus(): String {
		return try {
			if (status == "운영중") {
				"운행중인 슬로프 ${openSlopes}개"
			} else {
				val (year, month, day) = openingDate.split("-").map { it.toInt() }
				"${month}월 ${day}일 개장 예정이에요"
			}
		} catch (e: Exception) {
			"예상치 못한 이슈가 발생했어요"
		}
	}

	fun updateBySkiResortInfo(skiResortInfo: SkiResortInfo): DetailState = this.copy(
		resortId = skiResortInfo.resortId,
		resortName = skiResortInfo.resortName,
		openSlopes = skiResortInfo.openSlopeCount,
		isBookmarked = skiResortInfo.isBookmarked,
		status = skiResortInfo.status,
		openingDate = skiResortInfo.openingDate,
	)

	fun updateByWeatherInfo(weatherInfo: SkiResortWeatherInfo) = this.copy(
		temperature = weatherInfo.currentWeather.temperature,
		weatherCondition = weatherInfo.currentWeather.condition,
		weatherDescription = weatherInfo.currentWeather.description,
		todayWeatherByTime = weatherInfo.todayWeatherByTime.toPersistentList(),
		weeklyWeather = weatherInfo.weeklyWeather.toPersistentList(),
	)

	fun updateByTotalSurvey(totalSurvey: TotalResortSnowQualitySurvey) = this.copy(
		totalResortSnowQualitySurvey = totalSurvey
	)
}

sealed interface DetailEffect : UiEffect {
	data object GoToBackScreen : DetailEffect

	data class GoToResortWebcamUrlConnect(
		val resortId: Long,
		val resortName: String,
		val webViewUrl: String
	) : DetailEffect

	data class ShareResortWebUrl(
		val webUrl: String
	) : DetailEffect

	data class ShowSnackBar(val message: String, val action: String?) : DetailEffect
}
