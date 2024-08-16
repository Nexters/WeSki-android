package com.dieski.weski.presentation.detail

import com.dieski.weski.presentation.core.base.UiEffect
import com.dieski.weski.presentation.core.base.UiEvent
import com.dieski.weski.presentation.core.base.UiState
import com.dieski.weski.presentation.detail.model.ResortBriefData

class DetailContract {

    sealed interface Event : UiEvent {
        data class FetchingSkiResortData(val skiResortName: String) : Event
    }

    sealed interface State : UiState {

        data object Idle : State

        data object Loading : State

        data class Success(
            val resortBriefData: ResortBriefData
        ) : State
    }

    sealed class Effect : UiEffect
}