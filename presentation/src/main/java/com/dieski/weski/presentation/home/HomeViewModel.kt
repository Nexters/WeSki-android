package com.dieski.weski.presentation.home

import androidx.lifecycle.viewModelScope
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.usecase.GetResortWeatherInfoListUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.home.model.HomeResortWeatherInfo
import com.dieski.weski.presentation.home.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getResortWeatherInfoListUseCase: GetResortWeatherInfoListUseCase
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
			is HomeContract.Event.FetchingHomeData -> {
				fetchHomeData()
			}
		}
	}

	private fun fetchHomeData() {
		viewModelScope.launch {
			// Set Loading
			setState { HomeContract.State.Loading }
			delay(500L)

			val resortWeatherInfo = getResortWeatherInfoListUseCase()
			setState {
				HomeContract.State.Success(resortWeatherInfo.map(ResortWeatherInfo::toUiModel).toPersistentList())
			}
		}
	}
}