package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.ResortApiData
import com.dieski.domain.model.ResortApiWeatherData
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.core.model.WeatherType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val weSkiRepository: WeSkiRepository,
	@Named("fake") private val fakeWeSkiRepository: WeSkiRepository
) : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

	override fun createInitialState(): DetailContract.State {
		return DetailContract.State()
	}

	override fun handleEvent(event: DetailContract.Event) {
		when (event) {
			is DetailContract.Event.Init -> {
				fetchSkiResortData(
					event.resortId,
					event.resortName,
					event.resortWebKey,
					event.temperature,
					event.weatherType,
					event.weatherDescription
				)
			}

			is DetailContract.Event.SubmitSnowQualitySurvey -> {
				submitSnowQualitySurvey(
					event.resortId,
					event.isLike
				)
			}
		}
	}


	private fun fetchSkiResortData(
		resortId: Int,
		resortName: String,
		resortWebKey: String,
		temperature: Int,
		weatherType: WeatherType,
		weatherDescription: String
	) {
		viewModelScope.launch {
			val todayForecast = ResortApiWeatherData.entries.firstOrNull { it.key == resortId }?.todayForecast ?: ResortApiWeatherData.JISAN.todayForecast
			val weekForecast =  ResortApiWeatherData.entries.firstOrNull { it.key == resortId }?.weekForecast ?: ResortApiWeatherData.JISAN.weekForecast
			val snowMakingSurveyResult = weSkiRepository.fetchingSnowQualitySurveyResult(resortId) ?: SnowMakingSurveyResult()
			setState {
				copy(
					resortId = resortId,
					resortName = resortName,
					resortWebKey = resortWebKey,
					temperature = temperature,
					weatherType = weatherType,
					weatherDescription  = weatherDescription,
					todayForecast = todayForecast,
					weekForecast = weekForecast,
					snowMakingSurveyResult = snowMakingSurveyResult
				)
			}
		}
	}

	private fun submitSnowQualitySurvey(
		resortId: Int,
		isLike: Boolean
	) {
		viewModelScope.launch {
			weSkiRepository.submitSnowQualitySurvey(resortId, isLike)
			val snowMakingSurveyResult = weSkiRepository.fetchingSnowQualitySurveyResult(resortId) ?: return@launch
			setState {
				copy(
					snowMakingSurveyResult = snowMakingSurveyResult
				)
			}
		}
	}
}