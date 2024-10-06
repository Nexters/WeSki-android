package com.dieski.weski.presentation.detail

import androidx.compose.runtime.Immutable
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WebMobileData.Companion.SLOPE_PARAM
import com.dieski.domain.model.WebMobileData.Companion.WEBCAM_PARAM
import com.dieski.domain.model.WebMobileData.Companion.WEB_MOBILE_URL
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState

sealed interface DetailEvent : UiEvent {
	data class Init(
		val resortId: Long,
		val resortName: String,
		val resortWebKey: String,
		val temperature: Int,
		val weatherCondition: WeatherCondition,
		val weatherDescription: String
	) : DetailEvent

	data object ClickBackButton : DetailEvent

	data object ClickShareButton : DetailEvent

	data class ShowSnackBar(
		val message: String,
		val action: String?
	) : DetailEvent

	data class SubmitSnowQualitySurvey(
		val isLike: Boolean
	) : DetailEvent
}

@Immutable
data class DetailState(
	val resortId: Long = 0L,
	val resortName: String = "",
	val resortWebKey: String = "",
	val temperature: Int = 0,
	val weatherCondition: WeatherCondition = WeatherCondition.SUNNY,
	val weatherDescription: String = "",
	val todayForecast: TodayForecast = TodayForecast(),
	val weekForecast: List<WeekWeatherInfo> = emptyList(),
	val snowMakingSurveyResult: SnowMakingSurveyResult = SnowMakingSurveyResult()
) : UiState {
	val webcamWebUrl get() = "${WEB_MOBILE_URL}${WEBCAM_PARAM}$resortWebKey"
	val slopeWebUrl get() = "${WEB_MOBILE_URL}${SLOPE_PARAM}$resortWebKey"
}

sealed interface DetailEffect : UiEffect {
	data object GoToBackScreen : DetailEffect

	data class ShareResortWebUrl(
		val webUrl: String
	) : DetailEffect

	data class ShowSnackBar(val message: String, val action: String?) : DetailEffect
}
