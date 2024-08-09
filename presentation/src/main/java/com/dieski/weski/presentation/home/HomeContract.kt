package com.dieski.weski.presentation.home

import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import com.dieski.weski.presentation.home.model.HomeWeatherUiModel

class HomeContract {

    sealed interface Event : UiEvent {
        data object FetchingHomeData : Event
    }

    sealed interface State : UiState {

        data object Loading : State

        data class Success(val weatherList: List<HomeWeatherUiModel>) : State
    }

    sealed class Effect : UiEffect {

    }
}