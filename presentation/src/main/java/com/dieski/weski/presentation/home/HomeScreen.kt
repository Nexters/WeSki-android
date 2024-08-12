package com.dieski.weski.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.button.scroll.ScrollFloatButton
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCardWithWeatherCarousel
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.home.model.HomeWeatherUiModel
import kotlinx.coroutines.launch

@Composable
internal fun HomeRouter(
	padding: PaddingValues,
	navigateToDetail: (HomeWeatherUiModel) -> Unit,
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
	viewModel: HomeViewModel = hiltViewModel()
) {
	val state: HomeContract.State by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(Unit) {
		viewModel.handleEvent(HomeContract.Event.FetchingHomeData)
	}

	HomeScreen(
		onShowErrorSnackBar = onShowErrorSnackBar,
		state = state,
		navigateToDetail = navigateToDetail,
		modifier = Modifier
			.fillMaxSize()
			.padding(padding),
	)
}

@Composable
private fun HomeScreen(
	state: HomeContract.State,
	modifier: Modifier = Modifier,
	navigateToDetail: (HomeWeatherUiModel) -> Unit = {},
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit = {},
) {
	val lazyListState = rememberLazyListState()
	val coroutineScope = rememberCoroutineScope()
	val isTop by remember { derivedStateOf {  lazyListState.canScrollBackward }}

	Box(
		modifier = modifier.fillMaxSize()
	) {
		Column(
			modifier = Modifier.fillMaxSize()
				.background(WeskiColor.Main05)
		) {
			WeskiHeader(
				showBackButton = false,
				showShareButton = false
			)

			Spacer(modifier = Modifier.height(19.dp))

			Box(
				modifier = Modifier.fillMaxSize()
			) {
				if (state is HomeContract.State.Success) {
					HomeContent(
						weatherList = state.weatherList,
						onCardClick = navigateToDetail,
						modifier = Modifier.fillMaxSize(),
						lazyListState = lazyListState
					)
				} else {
					LoadingIndicator()
				}

				if (isTop) {
					ScrollFloatButton(
						modifier = Modifier
							.zIndex(1f)
							.align(Alignment.BottomEnd)
							.offset(x = (-20).dp, y = (-20).dp),
						onClick = {
							coroutineScope.launch {
								lazyListState.animateScrollToItem(0)
							}
						}
					) {
						Icon(
							modifier = Modifier
								.padding(12.dp)
								.size(18.dp),
							painter = painterResource(id = R.drawable.ic_arrow_up),
							contentDescription = "위로 가기",
							tint = WeskiColor.Gray60
						)
					}
				}
			}
		}
	}
}

@Composable
internal fun HomeContent(
	weatherList: List<HomeWeatherUiModel>,
	modifier: Modifier = Modifier,
	onCardClick: (HomeWeatherUiModel) -> Unit = {},
	lazyListState: LazyListState = rememberLazyListState()
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 20.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		state = lazyListState
	) {
		items(
			weatherList
		) { weather ->
			key(weather.id) {
				DiscoverCardWithWeatherCarousel(
					onClick = { onCardClick(weather) }
				)
			}
		}
	}
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val homeWeatherUiModelList = listOf(
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
	HomeScreen(
        state = HomeContract.State.Success(homeWeatherUiModelList)
    )
}