package com.dieski.weski.presentation.home

import androidx.lifecycle.viewModelScope
import com.dieski.analytics.AnalyticsLogger
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.usecase.GetResortWeatherInfoListUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.home.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
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
			// Set Loading
			setState { copy(isLoading = true) }
			delay(500L)

			val resortWeatherInfo = getResortWeatherInfoListUseCase()
			setState {
				copy(
					isLoading = false,
					resortWeatherInfoList = resortWeatherInfo.map(ResortWeatherInfo::toUiModel).toPersistentList()
				)
			}
		}
	}
}