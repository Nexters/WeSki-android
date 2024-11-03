package com.dieski.weski.presentation.detail

import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.domain.model.SkiResortWebKey
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.model.WeatherCondition
import com.dieski.domain.model.WebMobileData
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCard
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.snowflake.WindBlownSnowflakeEffectBackground
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.collectWithLifecycle
import com.dieski.weski.presentation.detail.component.DetailFeedTab
import com.dieski.weski.presentation.detail.component.DetailViewPagerWithTab
import com.dieski.weski.presentation.detail.component.TabItem
import com.dieski.weski.presentation.detail.congestion.CongestionScreen
import com.dieski.weski.presentation.detail.weather.WeatherScreen
import com.dieski.weski.presentation.detail.webcam.WebcamScreen
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
@Composable
internal fun DetailRouter(
	resortId: Long,
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (message: String, action: String?) -> Unit,
	viewModel: DetailViewModel = hiltViewModel()
) {
	val state by viewModel.uiState.collectAsStateWithLifecycle()
	val context = LocalContext.current

	viewModel.effects.collectWithLifecycle {
		when (it) {
			is DetailEffect.GoToBackScreen -> onNavigateUp()
			is DetailEffect.ShowSnackBar -> onShowSnackBar(it.message, it.action)
			is DetailEffect.ShareResortWebUrl -> {
				val intent = Intent(Intent.ACTION_SEND).apply {
					type = "text/plain"
					putExtra(
						Intent.EXTRA_TEXT,
						"${WebMobileData.WEB_MOBILE_URL}${WebMobileData.WEBCAM_PARAM}/${state.resortWebKey}"
					)
				}
				context.startActivity(Intent.createChooser(intent, "${state.resortName}을 공유해보세요!"))
			}
		}

	}

	LaunchedEffect(Unit) {
		viewModel.handleEvent(
			DetailEvent.Init(resortId)
		)
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		WindBlownSnowflakeEffectBackground(
			modifier = Modifier
				.fillMaxSize()
				.background(androidx.compose.ui.graphics.Color.Transparent)
		)

		DetailScreen(
			state = state,
			onAction = viewModel::handleEvent,
			modifier = Modifier
				.fillMaxSize()
				.padding(padding)
		)
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DetailScreen(
	state: DetailState,
	modifier: Modifier = Modifier,
	onAction: (DetailEvent) -> Unit = {},
) {
	val lazyListState = rememberLazyListState()
	var cardVisibility by remember { mutableStateOf(true) }

	val tabItemList = persistentListOf(
		TabItem("웹캠 정보"),
		TabItem("날씨"),
		TabItem("슬로프"),
	)

	val coroutineScope = rememberCoroutineScope()

	val pagerState = rememberPagerState(
		initialPage = 0,
		initialPageOffsetFraction = 0f,
		pageCount = { tabItemList.size }
	)

	LaunchedEffect(pagerState.currentPage) {
		snapshotFlow { pagerState.currentPage }
			.collect { currentPage ->
				pagerState.animateScrollToPage(currentPage)
			}
	}

	Column(
		modifier = modifier.fillMaxSize(),
	) {
		WeskiHeader(
			bgColor = if (cardVisibility) Color.Transparent else Color.White,
			showBackButton = true,
			showShareButton = true,
			title = if (cardVisibility) null else state.resortName,
			onClickBackButton = { onAction(DetailEvent.ClickBackButton) },
			onShare = {
				onAction(DetailEvent.ClickShareButton)
			}
		)
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			state = lazyListState
		) {
			item {
				DiscoverCard(
					modifier = Modifier
						.padding(vertical = 28.dp, horizontal = 21.dp)
						.isElementVisible {
							cardVisibility = it
						},
					resortName = state.resortName,
					operatingSlopeCount = 5,
					status = "",
					currentTemperature = state.temperature,
					weatherCondition = state.weatherCondition,
				)
			}

			stickyHeader {
				DetailFeedTab(
					modifier = Modifier
						.fillMaxWidth()
						.zIndex(1f),
					tabs = tabItemList,
					currentPage = pagerState.currentPage,
					tabIdx = pagerState.currentPage,
					onTabClick = { index ->
						coroutineScope.launch {
							pagerState.scrollToPage(index)
						}
					},
				)
			}

			item {
				if (state.resortWebKey != SkiResortWebKey.NONE) {
					HorizontalPager(
						modifier = Modifier
							.fillMaxSize()
							.background(WeskiColor.White),
						state = pagerState,
					) { page ->
						when (page) {
							0 -> WebcamScreen(
								state = state,
								isCurrentPage = pagerState.currentPage == 0,
								submitSnowQualitySurvey = {
									onAction(DetailEvent.SubmitSnowQualitySurvey(it))
								},
								onShowSnackBar = { message, action ->
									onAction(DetailEvent.ShowSnackBar(message, action))
								}
							)

							1 -> WeatherScreen(
								state = state,
								submitSnowQualitySurvey = {
									onAction(DetailEvent.SubmitSnowQualitySurvey(it))
								},
								onShowSnackBar = { message, action ->
									onAction(DetailEvent.ShowSnackBar(message, action))
								}
							)

							2 -> CongestionScreen(
								state = state,
								submitSnowQualitySurvey = {
									onAction(DetailEvent.SubmitSnowQualitySurvey(it))
								},
								isCurrentPage = pagerState.currentPage == 2,
								onShowSnackBar = { message, action ->
									onAction(DetailEvent.ShowSnackBar(message, action))
								}
							)
						}
					}
				}
			}
		}
	}
}

private fun Modifier.isElementVisible(onVisibilityChanged: (Boolean) -> Unit) = composed {
	val isVisible by remember { derivedStateOf { mutableStateOf(false) } }
	LaunchedEffect(isVisible.value) { onVisibilityChanged.invoke(isVisible.value) }
	this.onGloballyPositioned { layoutCoordinates ->
		isVisible.value = layoutCoordinates.parentLayoutCoordinates?.let {
			val parentBounds = it.boundsInWindow()
			val childBounds = layoutCoordinates.boundsInWindow()
			val childHalfBounds = Rect(
				left = childBounds.left,
				bottom = childBounds.bottom,
				right = childBounds.right,
				top = childBounds.top
			)
			parentBounds.overlaps(childHalfBounds)
		} ?: false
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun DetailScreenPreview() {
	DetailScreen(
		state = DetailState(
			resortId = 0,
			resortName = "용평스키장 모나",
			resortWebKey = SkiResortWebKey.EDEN,
			temperature = 7,
			weatherCondition = WeatherCondition.SNOW,
			weatherDescription = "눈이 내립니다.",
			snowQualitySurveyResult = SnowQualitySurveyResult(10, 5, 3, "")
		)
	)
}