package com.dieski.weski.presentation.detail

import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState

class DetailContract {

    sealed interface Event : UiEvent {
        data class FetchingSkiResortData(val skiResortName: String) : Event
    }

    sealed interface State : UiState {

        data object Idle : State

        data object Loading : State

        data object Success : State
    }

    sealed class Effect : UiEffect
}