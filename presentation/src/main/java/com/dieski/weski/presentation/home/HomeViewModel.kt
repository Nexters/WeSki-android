package com.dieski.weski.presentation.home

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.usecase.DeleteResortBookmarkUseCase
import com.dieski.domain.usecase.GetSkiResortListUseCase
import com.dieski.domain.usecase.SaveResortBookmarkUseCase
import com.dieski.weski.presentation.core.base.BaseViewModel
import com.dieski.weski.presentation.home.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getSkiResortListUseCase: GetSkiResortListUseCase,
	private val saveResortBookmarkUseCase: SaveResortBookmarkUseCase,
	private val deleteResortBookmarkUseCase: DeleteResortBookmarkUseCase
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

			is HomeEvent.ToggleBookmark -> {
				val (resortId, isBookmarked) = event
				toggleResortBookmarked(resortId, isBookmarked)
			}

			HomeEvent.ClickShowServiceInfoReport -> setEffect(HomeEffect.ShowServiceInfoReport)
			HomeEvent.ClickReportBug -> setEffect(HomeEffect.ReportBug)
			HomeEvent.ClickWriteFeedbackReport -> setEffect(HomeEffect.WriteFeedbackReport)
		}
	}

	private fun fetchHomeData() {
		launch {
			setState { copy(isLoading = true) }
			val skiResortList = getSkiResortListUseCase().first()

			setState {
				copy(
					isLoading = false,
					resortWeatherInfoList = skiResortList.map(SkiResortInfo::toUiModel).toPersistentList()
				)
			}
		}
	}

	private fun toggleResortBookmarked(resortId: Long, isBookmarked:Boolean) {
		launch {
			val bookmarkResult: Result<Unit> = if(isBookmarked.not()) {
				saveResortBookmarkUseCase(resortId)
			} else {
				deleteResortBookmarkUseCase(resortId)
			}

			if (bookmarkResult.isSuccess) {
				fetchHomeData()
				if (isBookmarked.not()) {
					setEffect(HomeEffect.OpenBookmarkPopup)
				}
			}
		}
	}
}