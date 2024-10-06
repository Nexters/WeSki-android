package com.dieski.weski.presentation.home

import androidx.lifecycle.viewModelScope
import com.dieski.analytics.AnalyticsLogger
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.domain.usecase.GetResortWeatherInfoListUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.detail.DetailEffect
import com.dieski.weski.presentation.home.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getResortWeatherInfoListUseCase: GetResortWeatherInfoListUseCase,
	private val logger: AnalyticsLogger
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

	/**
	 * Create initial State of Views
	 */
	override fun createInitialState(): HomeState {
		return HomeState(isLoading = true)
	}

	/**
	 * Handle each event
	 */
	override fun handleEvent(event: HomeEvent) {
		when (event) {
			is HomeEvent.FetchingHomeData -> fetchHomeData()
			is HomeEvent.ClickCard -> setEffect(
				HomeEffect.NavigateToDetail(event.resortWeatherInfo)
			)
			is HomeEvent.ClickScrollFloatButton -> setEffect(
				HomeEffect.ScrollToTop
			)
		}
	}

	private fun fetchHomeData() {
		viewModelScope.launch {
			setState { copy(isLoading = true) }

			val skiResortInfoListResult = when(val result = getResortWeatherInfoListUseCase()) {
				is WResult.Success -> result.data
				is WResult.Error -> {
					when(result.error) {
						is DataError.Network.ServerError -> {
							setEffect(HomeEffect.ShowSnackBar("서버 에러가 발생하여 스키장 정보를 불러오지 못했습니다", null))
						}
						is DataError.Network.TimeoutError -> {
							setEffect(HomeEffect.ShowSnackBar("응답 시간을 초과하여 스키장 정보를 불러오지 못했습니다", null))
						}
					}
					emptyList()
				}
			}

			setState {
				copy(
					isLoading = false,
					resortWeatherInfoList = skiResortInfoListResult.map(SkiResortInfo::toUiModel).toPersistentList()
				)
			}
		}
	}
}