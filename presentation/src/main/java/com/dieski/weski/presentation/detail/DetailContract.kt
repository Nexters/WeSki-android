package com.dieski.weski.presentation.detail

import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import com.dieski.weski.presentation.core.model.WeatherType
import com.dieski.weski.presentation.detail.model.ResortBriefData

class DetailContract {

    sealed interface Event : UiEvent {
        data class Init(
            val resortId: Int,
            val resortName: String,
            val resortWebKey: String,
            val temperature: Int,
            val weatherType: WeatherType,
            val weatherDescription: String
        ) : Event

        data class SubmitSnowQualitySurvey(
            val resortId: Int,
            val isLike: Boolean
        ) : Event
    }

    data class State(
        val resortId: Int = 0,
        val resortName: String = "",
        val resortWebKey: String = "",
        val temperature: Int = 0,
        val weatherType: WeatherType = WeatherType.NORMAL,
        val weatherDescription: String = "",
        val todayForecast: TodayForecast = TodayForecast(),
        val weekForecast: List<WeekWeatherInfo> = emptyList(),
        val snowMakingSurveyResult: SnowMakingSurveyResult = SnowMakingSurveyResult()
    ) : UiState

    sealed class Effect : UiEffect
}