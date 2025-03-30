package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.exception.SnowSurveyAlreadyExistException
import com.dieski.domain.usecase.DeleteResortBookmarkUseCase
import com.dieski.domain.usecase.GetSkiResortListUseCase
import com.dieski.domain.usecase.GetSkiResortWeatherInfoUseCase
import com.dieski.domain.usecase.GetTotalResortSnowQualitySurveyUseCase
import com.dieski.domain.usecase.SaveResortBookmarkUseCase
import com.dieski.domain.usecase.SubmitSnowQualitySurveyResultUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val getSkiResortListUseCase: GetSkiResortListUseCase,
	private val getSkiResortWeatherInfoUseCase: GetSkiResortWeatherInfoUseCase,
	private val getTotalResortSnowQualitySurveyUseCase: GetTotalResortSnowQualitySurveyUseCase,
	private val submitSnowQualitySurveyUseCase: SubmitSnowQualitySurveyResultUseCase,
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
				setEffect(DetailEffect.GoToResortWebcamUrlConnect(uiState.value.resortId, uiState.value.resortName, event.webViewUrl))
			}
		}
	}

	private fun fetchSkiResortData(resortId: Long) {
		launch {
			val skiResortInfo = getSkiResortListUseCase().first().firstOrNull { it.resortId == resortId } ?: return@launch
			Timber.d("DetailViewModel", "fetchSkiResortData: $skiResortInfo")
			setState {
				this.updateBySkiResortInfo(skiResortInfo)
			}
		}

		launch {
			val skiResortWeatherInfo = getSkiResortWeatherInfoUseCase(resortId).first()
			Timber.d("DetailViewModel", "skiResortWeatherInfo: $skiResortWeatherInfo")
			setState {
				this.updateByWeatherInfo(skiResortWeatherInfo)
			}
		}

		launch {
			val totalResortSnowQualitySurvey = getTotalResortSnowQualitySurveyUseCase(resortId).first()
			Timber.d("DetailViewModel", "totalResortSnowQualitySurvey: $totalResortSnowQualitySurvey")
			setState {
				this.updateByTotalSurvey(totalResortSnowQualitySurvey)
			}
		}
	}

	private fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	) {
		launch {
			val submitSurvey = submitSnowQualitySurveyUseCase(resortId, isPositive)
			if (submitSurvey.isSuccess) {
				setEffect(DetailEffect.ShowSnackBar("고마워요! 투표의 결과가 반영되었어요", null))
				loadTotalSnowQualitySurvey(resortId)
			} else {
				when(submitSurvey.exceptionOrNull()) {
					is SnowSurveyAlreadyExistException -> {
						setEffect(DetailEffect.ShowSnackBar("오늘은 이미 설문조사를 완료하셨습니다.", null))
					}
					else -> {
						setEffect(DetailEffect.ShowSnackBar("예상치 못한 문제가 발생하여 설문 결과가 저장되지 않았습니다", null))
					}
				}
			}
		}
	}

	private suspend fun loadTotalSnowQualitySurvey(resortId: Long) {
		val totalResortSnowQualitySurvey = getTotalResortSnowQualitySurveyUseCase(resortId).first()
		setState {
			this.updateByTotalSurvey(totalResortSnowQualitySurvey)
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