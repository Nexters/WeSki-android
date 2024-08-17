package com.dieski.weski.presentation.home

import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import com.dieski.weski.presentation.home.model.HomeResortWeatherInfo
import kotlinx.collections.immutable.PersistentList

class HomeContract {

    sealed interface Event : UiEvent {
        data object FetchingHomeData : Event
    }

    sealed interface State : UiState {

        data object Loading : State

        data class Success(val resortWeatherInfoList: PersistentList<HomeResortWeatherInfo>) : State
    }

    sealed interface Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect
    }
}