package com.dieski.weski.presentation.home

import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import com.dieski.weski.presentation.home.model.HomeSkiResortInfo
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface HomeEvent : UiEvent {
	data object FetchingHomeData : HomeEvent

	data class ClickCard(
		val resortWeatherInfo: HomeSkiResortInfo
	) : HomeEvent

	data object ClickScrollFloatButton : HomeEvent
}

data class HomeState(
	val isLoading: Boolean = false,
	val resortWeatherInfoList: PersistentList<HomeSkiResortInfo> = persistentListOf()
) : UiState

sealed interface HomeEffect : UiEffect {
	data class NavigateToDetail(
		val resortWeatherInfo: HomeSkiResortInfo
	) : HomeEffect

	data object ScrollToTop : HomeEffect

	data class ShowSnackBar(val message: String, val action: String?) : HomeEffect
}