package com.dieski.weski.presentation.home

import androidx.lifecycle.viewModelScope
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.home.model.HomeWeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    /**
     * Create initial State of Views
     */
    override fun createInitialState(): HomeContract.State {
        return HomeContract.State.Loading
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.FetchingHomeData -> { fetchHomeData() }
        }
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            // Set Loading
            setState { HomeContract.State.Loading }
            delay(500L)
            setState {
                HomeContract.State.Success(
                    listOf(
                        HomeWeatherUiModel(
                            id = 1L,
                            skiResortName = "리조트1"
                        ),
                        HomeWeatherUiModel(
                            id = 2L,
                            skiResortName = "리조트1"
                        ),
                        HomeWeatherUiModel(
                            id = 3L,
                            skiResortName = "리조트1"
                        ),
                        HomeWeatherUiModel(
                            id = 4L,
                            skiResortName = "리조트1"
                        )
                    )
                )
            }
        }
    }
}