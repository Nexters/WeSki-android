package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.SkiResortDetailInfo
import com.dieski.domain.model.result.DetailError
import com.dieski.domain.model.result.SubmitError
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.domain.usecase.DeleteResortBookmarkUseCase
import com.dieski.domain.usecase.GetSkiResortDetailInfoUseCase
import com.dieski.domain.usecase.SaveResortBookmarkUseCase
import com.dieski.domain.usecase.SubmitSnowQualitySurveyResultUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val getSkiResortDetailInfoUseCase: GetSkiResortDetailInfoUseCase,
	private val submitSnowQualitySurveyResultUseCase: SubmitSnowQualitySurveyResultUseCase,
	private val saveResortBookmarkUseCase: SaveResortBookmarkUseCase,
	private val deleteResortBookmarkUseCase: DeleteResortBookmarkUseCase,
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

			is DetailEvent.ToggleBookmark -> {
				val (resortId, isBookmarked) = event
				viewModelScope.launch {
					toggleResortBookmarked(resortId, isBookmarked)
					fetchSkiResortData(resortId)
				}
			}

			is DetailEvent.ShowSnackBar -> {
				setEffect(DetailEffect.ShowSnackBar(event.message, event.action))
			}

			is DetailEvent.ClickWebcam -> {
				setEffect(DetailEffect.GoToResortWebcamUrlConnect(event.resortId, event.resortName, event.webViewUrl))
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
					openSlopes = skiResortDetailInfo.skiResortInfo.openSlopeCount,
					isBookmarked = skiResortDetailInfo.skiResortInfo.isBookmarked,
					status = skiResortDetailInfo.skiResortInfo.status,
					openingDate = skiResortDetailInfo.skiResortInfo.openingDate,
					temperature = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.temperature,
					weatherCondition = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.condition,
					weatherDescription = skiResortDetailInfo.skiResortWeatherInfo.currentWeather.description,
					todayWeatherByTime = skiResortDetailInfo.skiResortWeatherInfo.todayWeatherByTime,
					weeklyWeather = skiResortDetailInfo.skiResortWeatherInfo.weeklyWeather,
					snowQualitySurveyResult = skiResortDetailInfo.snowQualitySurveyResult
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
						is SubmitError -> {
							setEffect(DetailEffect.ShowSnackBar("오늘은 이미 설문조사를 완료하셨습니다.", null))
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

	private suspend fun toggleResortBookmarked(resortId: Long, isBookmarked:Boolean) {
		if(isBookmarked.not()) {
			saveResortBookmarkUseCase(resortId)
		} else {
			deleteResortBookmarkUseCase(resortId)
		}
	}
}