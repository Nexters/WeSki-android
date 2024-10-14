package com.dieski.weski.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dieski.analytics.AnalyticsLogger
import com.dieski.domain.model.SkiResortDetailInfo
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.model.result.DetailError
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.domain.usecase.GetAllSkiResortsUseCase
import com.dieski.domain.usecase.GetSkiResortDetailInfoUseCase
import com.dieski.domain.usecase.GetSkiResortWeatherInfoUseCase
import com.dieski.domain.usecase.SubmitSnowQualitySurveyResultUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val getSkiResortDetailInfoUseCase: GetSkiResortDetailInfoUseCase,
	private val submitSnowQualitySurveyResultUseCase: SubmitSnowQualitySurveyResultUseCase,
	private val logger : AnalyticsLogger,
) : BaseViewModel<DetailEvent, DetailState, DetailEffect>() {

	override fun createInitialState(): DetailState {
		return DetailState()
	}

	override fun handleEvent(event: DetailEvent) {
		when (event) {
			is DetailEvent.Init -> {
				fetchSkiResortData(
					resortId = event.resortId
				)
			}

			is DetailEvent.SubmitSnowQualitySurvey -> {
				submitSnowQualitySurvey(
					resortId = uiState.value.resortId,
					isPositive = event.isPositive
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
	) {
		viewModelScope.launch {
			val skiResortDetailInfo: SkiResortDetailInfo = when(val result = getSkiResortDetailInfoUseCase(resortId)) {
				is WResult.Success -> result.data
				is WResult.Error -> {
					when(result.error) {
						is DetailError -> {
							setEffect(DetailEffect.ShowSnackBar((result.error as DetailError).message, null))
						}
						is DataError.Network.ServerError -> {
							setEffect(DetailEffect.ShowSnackBar("서버 에러가 발생하여 설문 결과를 불러오지 못했습니다.", null))
						}
						is DataError.Network.TimeoutError -> {
							setEffect(DetailEffect.ShowSnackBar("응답 시간을 초과하여 설문 결과를 불러오지 못했습니다.", null))
						}
					}
					return@launch
				}
			}

			setState {
				copy(
					resortId = skiResortDetailInfo.skiResortInfo.resortId,
					resortName = skiResortDetailInfo.skiResortInfo.resortName,
					resortWebKey = skiResortDetailInfo.skiResortInfo.resortWebKey,
					temperature = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.temperature,
					weatherCondition = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.condition,
					weatherDescription = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.description,
					todayWeatherByTime = skiResortDetailInfo.skiResortWeatherInfo.todayWeatherByTime,
					weeklyWeather = skiResortDetailInfo.skiResortWeatherInfo.weeklyWeather,
					snowQualitySurveyResult = snowQualitySurveyResult
				)
			}
		}
	}

	private fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	) {
		viewModelScope.launch {
			val snowMakingSurveyResult = when(val result = submitSnowQualitySurveyResultUseCase(resortId, isPositive)) {
				is WResult.Success -> {
					setEffect(DetailEffect.ShowSnackBar("고마워요! 투표의 결과가 반영되었어요", null))
					result.data
				}
				is WResult.Error -> {
					when (result.error) {
						is DataError.Network.ServerError -> {
							setEffect(DetailEffect.ShowSnackBar("서버 에러가 발생하여 설문 결과가 저장되지 않았습니다.", null))
						}
						is DataError.Network.TimeoutError -> {
							setEffect(DetailEffect.ShowSnackBar("응답 시간을 초과하여 설문 결과를 불러오지 못했습니다.", null))
						}
					}
					return@launch
				}
			}

				setState {
					copy(
						snowQualitySurveyResult = snowMakingSurveyResult
					)
				}
			}
		}
}