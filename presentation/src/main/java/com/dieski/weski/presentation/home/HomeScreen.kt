package com.dieski.weski.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.SkiResortWebKey
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.snowflake.WindBlownSnowflakeEffect
import com.dieski.weski.presentation.core.designsystem.button.scroll.ScrollFloatButton
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCardWithWeatherCarousel
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.collectWithLifecycle
import com.dieski.weski.presentation.home.model.HomeSkiResortInfo
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

@Composable
internal fun HomeRouter(
	padding: PaddingValues,
	navigateToDetail: (HomeSkiResortInfo) -> Unit,
	onShowSnackBar: (message: String, action: String?) -> Unit,
	viewModel: HomeViewModel = hiltViewModel()
) {
	val state by viewModel.uiState.collectAsStateWithLifecycle()
	val coroutineScope = rememberCoroutineScope()
	val lazyListState = rememberLazyListState()

	viewModel.effects.collectWithLifecycle {
		when (it) {
			is HomeEffect.NavigateToDetail -> navigateToDetail(it.resortWeatherInfo)
			is HomeEffect.ShowSnackBar -> onShowSnackBar(it.message, it.action)
			is HomeEffect.ScrollToTop -> {
				coroutineScope.launch {
					lazyListState.animateScrollToItem(0)
				}
			}
		}
	}

	LaunchedEffect(Unit) {
		viewModel.handleEvent(HomeEvent.FetchingHomeData)
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		Image(
			modifier = Modifier.fillMaxSize(),
			painter = painterResource(id = R.drawable.img_background),
			contentDescription = "",
			contentScale = ContentScale.FillBounds
		)

		WindBlownSnowflakeEffect()

		HomeScreen(
			state = state,
			onAction = viewModel::handleEvent,
			lazyListState = lazyListState,
			modifier = Modifier
				.fillMaxSize()
				.padding(padding),
		)
	}
}

@Composable
private fun HomeScreen(
	state: HomeState,
	onAction: (HomeEvent) -> Unit,
	modifier: Modifier = Modifier,
	lazyListState: LazyListState = rememberLazyListState(),
) {
	val isTop by remember { derivedStateOf { lazyListState.canScrollBackward } }

	Column(
		modifier = modifier
			.fillMaxSize()
	) {
		WeskiHeader(
			showBackButton = false,
			showShareButton = false
		)

		Spacer(modifier = Modifier.height(19.dp))

		Box(
			modifier = Modifier.fillMaxSize()
		) {
			if (!state.isLoading) {
				HomeContent(
					resortWeatherInfoList = state.resortWeatherInfoList,
					onCardClick = { onAction(HomeEvent.ClickCard(it)) },
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
						onAction(HomeEvent.ClickScrollFloatButton)
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

@Composable
internal fun HomeContent(
	resortWeatherInfoList: List<HomeSkiResortInfo>,
	modifier: Modifier = Modifier,
	onCardClick: (HomeSkiResortInfo) -> Unit = {},
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
			resortWeatherInfoList
		) { resortWeatherInfo ->
			key(resortWeatherInfo) {
				DiscoverCardWithWeatherCarousel(
					resortName = resortWeatherInfo.name,
					operatingSlopeCount = resortWeatherInfo.operatingSlopeCount,
					weatherCondition = resortWeatherInfo.currentWeather.condition,
					currentTemperature = resortWeatherInfo.currentWeather.temperature,
					weekWeatherInfoList = resortWeatherInfo.weeklyWeather,
					status = resortWeatherInfo.status,
					onClick = { onCardClick(resortWeatherInfo) }
				)
			}
		}

		item {
			Spacer(modifier = Modifier.height(45.dp))
		}
	}
}

@Preview
@Composable
private fun HomeScreenPreview() {
	val dailyWeatherLists = persistentListOf(
		DailyWeather(day = "월요일", condition = WeatherCondition.CLOUDY, maxTemperature = 2, minTemperature = -7),
		DailyWeather(day = "화요일", condition = WeatherCondition.CLOUDY, maxTemperature = 0, minTemperature = -7),
		DailyWeather(day = "수요일", condition = WeatherCondition.CLOUDY, maxTemperature = -5, minTemperature = -7),
		DailyWeather(day = "목요일", condition = WeatherCondition.CLOUDY, maxTemperature = -5, minTemperature = -7),
		DailyWeather(day = "금요일", condition = WeatherCondition.CLOUDY, maxTemperature = 5, minTemperature = -7),
		DailyWeather(day = "일요일", condition = WeatherCondition.CLOUDY, maxTemperature = 6, minTemperature = -7)
	)

	val resortWeatherInfoList = listOf(
		HomeSkiResortInfo(
			name = "용평스키장 모나",
			id = 0,
			webKey = SkiResortWebKey.O2,
			operatingSlopeCount = 5,
			currentWeather = SkiResortInfo.CurrentWeather(
				condition = WeatherCondition.CLOUDY,
				temperature = 7
			),
			status = "",
			weeklyWeather = dailyWeatherLists
		)
	)

	HomeScreen(
		state = HomeState(resortWeatherInfoList = resortWeatherInfoList.toPersistentList()),
		onAction = {}
	)
}