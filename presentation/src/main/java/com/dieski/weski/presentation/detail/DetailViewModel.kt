package com.dieski.weski.presentation.detail

import androidx.lifecycle.viewModelScope
import com.dieski.weski.presentation.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

    override fun createInitialState() = DetailContract.State.Loading

    override fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.FetchingSkiResortData -> { fetchSkiResortData(event.skiResortName) }
        }
    }

    private fun fetchSkiResortData(skiResortName: String) {
        viewModelScope.launch {
            setState { DetailContract.State.Loading }
            delay(500L)
            setState { DetailContract.State.Success }
        }
    }
}