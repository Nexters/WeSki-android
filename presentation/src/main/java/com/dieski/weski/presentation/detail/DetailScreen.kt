package com.dieski.weski.presentation.detail

import android.app.Activity
import android.content.Intent
import android.webkit.WebView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.domain.model.TotalResortSnowQualitySurvey
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.LocalLoggerOwner
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCard
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.snowflake.WindBlownSnowflakeEffectBackground
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.collectWithLifecycle
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.core.util.skiResortOpeningDatePassed
import com.dieski.weski.presentation.detail.component.DetailFeedTab
import com.dieski.weski.presentation.detail.component.DetailResortOpenNotificationDialog
import com.dieski.weski.presentation.detail.congestion.CongestionScreen
import com.dieski.weski.presentation.detail.model.TabItem
import com.dieski.weski.presentation.detail.weather.WeatherPage
import com.dieski.weski.presentation.detail.webcam.WebcamPage
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import com.dieski.weski.presentation.util.log
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.graphics.Color as AndroidColor

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
@Composable
internal fun DetailRouter(
	resortId: Long,
	padding: PaddingValues,
	navigateToWebcamConnect: (Long, String, String) -> Unit,
	onNavigateUp: () -> Unit,
	onShowSnackBar: (message: String, action: String?) -> Unit,
	viewModel: DetailViewModel = hiltViewModel()
) {
	// 2025년 스키장 개장 후엔 제거해도 됨.
	var showOpenNotificationDialog by remember { mutableStateOf(false) }
	var showPermissionSuccessDialog by remember { mutableStateOf(false) }
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
						it.webUrl
					)
				}
				context.startActivity(Intent.createChooser(intent, "${state.resortName}을 공유해보세요!"))
			}

			is DetailEffect.GoToResortWebcamUrlConnect -> {
				navigateToWebcamConnect(it.resortId, it.resortName, it.webViewUrl)
			}
		}
	}

	LaunchedEffect(Unit) {
		viewModel.handleEvent(
			DetailEvent.Init(resortId)
		)
	}

	LaunchedEffect(state.openingDate) {
		if (state.openingDate != "0000-00-00") {
			val isOpeningDateNotPassed = skiResortOpeningDatePassed(state.openingDate).not()
			if (isOpeningDateNotPassed) {
				showOpenNotificationDialog = true
			}
		}
	 }

	LaunchedEffect(showPermissionSuccessDialog) {
		 if(showPermissionSuccessDialog) {
			 delay(3000L)
			 showPermissionSuccessDialog = false
		 }
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		WindBlownSnowflakeEffectBackground(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.Transparent)
		)

		DetailScreen(
			state = state,
			onAction = viewModel::handleEvent,
			modifier = Modifier
				.fillMaxSize()
				.padding(padding)
		)

		if (showOpenNotificationDialog) {
			DetailResortOpenNotificationDialog(
				onDismiss = {
					showOpenNotificationDialog = false
				},
				onShowPermissionSuccessDialog = {
					showPermissionSuccessDialog = true
				}
			)
		}

		if (showPermissionSuccessDialog) {
			PermissionSuccessPopup(
				onDismiss = {}
			)
		}
	}
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun DetailScreen(
	state: DetailState,
	modifier: Modifier = Modifier,
	onAction: (DetailEvent) -> Unit = {}
) {
	val lazyListState = rememberLazyListState()
	var cardVisibility by remember { mutableStateOf(true) }
	val context  = LocalContext.current

	LaunchedEffect(cardVisibility) {
		if (context is Activity) {
			context.window.statusBarColor = if(cardVisibility) AndroidColor.TRANSPARENT else AndroidColor.WHITE
		}
	}

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

	var webcamWebView by remember { mutableStateOf<WebView?> (null) }
	var slopeWebView by remember { mutableStateOf<WebView?>(null) }
	val logger = LocalLoggerOwner.current

	LaunchedEffect(pagerState.currentPage) {
		snapshotFlow { pagerState.currentPage }
			.collect { currentPage ->
				if (state.resortName.isEmpty()) return@collect

				val eventName = if (currentPage == 0) {
					"details_tab_webcam"
				} else if (currentPage == 1) {
					"details_tab_weather"
				} else {
					"details_tab_slope"
				}
				logger.log(eventName, state.resortName)
				pagerState.animateScrollToPage(currentPage)
			}
	}

	Column(
		modifier = modifier.fillMaxSize(),
	) {
		WeskiHeader(
			title = if (cardVisibility) null else state.resortName,
			bgColor = if (cardVisibility) Color.Transparent else Color.White,
			startIcon = {
				Icon(
					modifier = Modifier
						.size(26.dp)
						.noRippleClickable { onAction(DetailEvent.ClickBackButton) },
					painter = painterResource(id = R.drawable.ic_arrow_back),
					tint = Color.Black,
					contentDescription = "뒤로가기"
				)
			},
			endIcon = {
				Icon(
					modifier = Modifier
						.size(26.dp)
						.noRippleClickable { onAction(DetailEvent.ClickShareButton) },
					painter = painterResource(id = R.drawable.icn_share),
					tint = WeskiColor.Gray90,
					contentDescription = "공유하기"
				)
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
					status = state.getResortOperatingStatus(),
					currentTemperature = state.temperature,
					weatherCondition = state.weatherCondition,
					isBookmarked = state.isBookmarked,
					onClickBookmark = {
						onAction(DetailEvent.ToggleBookmark(state.resortId, state.isBookmarked))
					}
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
				HorizontalPager(
					modifier = Modifier
						.fillMaxSize()
						.background(WeskiColor.White),
					state = pagerState,
					verticalAlignment = Alignment.Top
				) { page ->
					when (page) {
						0 -> WebcamPage(
							state = state,
							webView = webcamWebView,
							submitSnowQualitySurvey = { isLike ->
								logger.log("details_webcam_vote", state.resortName)
								onAction(DetailEvent.SubmitSnowQualitySurvey(isLike))
							},
							onShowSnackBar = { message, action ->
								onAction(DetailEvent.ShowSnackBar(message, action))
							},
							navigateToWebView = { url ->
								onAction(DetailEvent.ClickWebcam(url))
							},
							updateWebcamWebView = {
								webcamWebView = it
							}
						)

						1 -> WeatherPage(
							state = state,
							submitSnowQualitySurvey = { isLike ->
								val likeLoggerText = if (isLike) "good" else "not good"
								logger.log("details_weather_vote", state.resortName)
								onAction(DetailEvent.SubmitSnowQualitySurvey(isLike))
							},
							onShowSnackBar = { message, action ->
								onAction(DetailEvent.ShowSnackBar(message, action))
							},
							logWeatherTimeRowScrolling = { isScrolling ->
								if (isScrolling) {
									logger.log("details_weather_daily weather_swipe", state.resortName)
								}
							}
						)

						2 -> CongestionScreen(
							state = state,
							webView = slopeWebView,
							submitSnowQualitySurvey = { isLike ->
								val likeLoggerText = if (isLike) "good" else "not good"
								logger.log("details_slope_vote", state.resortName)
								onAction(DetailEvent.SubmitSnowQualitySurvey(isLike))
							},
							onShowSnackBar = { message, action ->
								onAction(DetailEvent.ShowSnackBar(message, action))
							},
							updateWebView = {
								slopeWebView = it
							}
						)
					}
				}
			}
		}
	}
}

@Composable
private fun PermissionSuccessPopup(
	onDismiss: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.fillMaxSize()
			.noRippleClickable { onDismiss() }
	) {
		Column(
			modifier = Modifier
				.widthIn(max = 316.dp)
				.background(color = Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(20.dp))
				.align(Alignment.Center)
				.padding(all = 24.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Icon(
				modifier = Modifier.size(32.dp),
				painter = painterResource(R.drawable.icn_check),
				contentDescription = "",
				tint = WeskiColor.White,
			)

			Spacer(Modifier.height(12.dp))

			Text(
				text = "오픈 알림 신청이 완료되었어요!",
				style = TextStyle(
					fontFamily = FontFamily(Font(R.font.pretendard_bold)),
					fontWeight = FontWeight.Bold,
					fontSize = 16.sp,
					color = Color.White,
					textAlign = TextAlign.Center
				),
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)
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
	WeskiTheme {
		DetailScreen(
			state = DetailState(
				resortId = 0,
				resortName = "용평스키장 모나",
				temperature = 7,
				weatherCondition = WeatherCondition.SNOW,
				openingDate = "2024-08-10",
				weatherDescription = "눈이 내립니다.",
				totalResortSnowQualitySurvey = TotalResortSnowQualitySurvey(10, 5, 3, "")
			)
		)
	}
}