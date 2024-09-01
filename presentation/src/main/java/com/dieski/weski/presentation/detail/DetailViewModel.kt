package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.ResortApiData
import com.dieski.domain.model.ResortApiWeatherData
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.WebMobileData
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
) : BaseViewModel<DetailEvent, DetailState, DetailEffect>() {

	override fun createInitialState(): DetailState {
		return DetailState()
	}

	override fun handleEvent(event: DetailEvent) {
		when (event) {
			is DetailEvent.Init -> {
				fetchSkiResortData(
					resortId = event.resortId,
					resortName = event.resortName,
					resortWebKey = event.resortWebKey,
					temperature = event.temperature,
					weatherType = event.weatherType,
					weatherDescription = event.weatherDescription
				)
			}

			is DetailEvent.SubmitSnowQualitySurvey -> {
				submitSnowQualitySurvey(
					resortId = uiState.value.resortId,
					isLike = event.isLike
				)
			}

			is DetailEvent.ClickBackButton -> {
				setEffect(DetailEffect.GoToBackScreen)
			}

			is DetailEvent.ClickShareButton -> setEffect(
				DetailEffect.ShareResortWebUrl(
					webUrl = uiState.value.webcamWebUrl
				)
			)

			is DetailEvent.ShowSnackBar -> {
				setEffect(DetailEffect.ShowSnackBar(event.message, event.action))
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