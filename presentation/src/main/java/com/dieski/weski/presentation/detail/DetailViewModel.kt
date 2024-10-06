package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.analytics.AnalyticsLogger
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.weski.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val snowQualityRepository: SnowQualityRepository,
	private val fakeWeSkiRepository: WeSkiRepository,
	private val logger : AnalyticsLogger
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
					weatherCondition = event.weatherCondition,
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
		resortId: Long,
		resortName: String,
		resortWebKey: String,
		temperature: Int,
		weatherCondition: WeatherCondition,
		weatherDescription: String
	) {
		viewModelScope.launch {
			val todayForecast = TodayForecast() //ResortApiWeatherData.entries.firstOrNull { it.key == resortId }?.todayForecast ?: ResortApiWeatherData.JISAN.todayForecast
			val weekForecast =  emptyList<WeekWeatherInfo>()
//				date = "",
//				day = "",
//				chanceOfRain = 0,
//				weatherType = "",
//				highestTemperature = 0,
//				lowestTemperature = 0
//			)
			//ResortApiWeatherData.entries.firstOrNull { it.key == resortId }?.weekForecast ?: ResortApiWeatherData.JISAN.weekForecast
			val snowMakingSurveyResult = when(val result = snowQualityRepository.fetchingSnowQualitySurveyResult(resortId)) {
				is WResult.Success -> result.data
				is WResult.Error -> {
					when(result.error) {
						is DataError.Network.ServerError -> {
							setEffect(DetailEffect.ShowSnackBar("서버 에러가 발생하여 설문 결과를 불러오지 못했습니다.", null))
						}
						is DataError.Network.TimeoutError -> {
							setEffect(DetailEffect.ShowSnackBar("응답 시간을 초과하여 설문 결과를 불러오지 못했습니다.", null))
						}
					}
					SnowMakingSurveyResult.EMPTY
				}
			}

			setState {
				copy(
					resortId = resortId,
					resortName = resortName,
					resortWebKey = resortWebKey,
					temperature = temperature,
					weatherCondition = weatherCondition,
					weatherDescription  = weatherDescription,
					todayForecast = todayForecast,
					weekForecast = weekForecast,
					snowMakingSurveyResult = snowMakingSurveyResult
				)
			}
		}
	}

	private fun submitSnowQualitySurvey(
		resortId: Long,
		isLike: Boolean
	) {
		viewModelScope.launch {
			snowQualityRepository.submitSnowQualitySurvey(resortId.toInt(), isLike)
			val snowMakingSurveyResult = when(val result = snowQualityRepository.fetchingSnowQualitySurveyResult(resortId)) {
				is WResult.Success -> result.data
				is WResult.Error -> {
					when (result.error) {
						is DataError.Network.ServerError -> {
							setEffect(DetailEffect.ShowSnackBar("서버 에러가 발생하여 설문 결과가 저장되지 않았습니다.", null))
						}
						is DataError.Network.TimeoutError -> {
							setEffect(DetailEffect.ShowSnackBar("응답 시간을 초과하여 설문 결과를 불러오지 못했습니다.", null))
						}
					}
					null
				}
			}

			if (snowMakingSurveyResult != null) {
				setState {
					copy(
						snowMakingSurveyResult = snowMakingSurveyResult
					)
				}
			}
		}
	}
}