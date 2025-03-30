package com.dieski.weski.presentation.home

import android.content.Intent
import android.net.Uri
import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.LocalLoggerOwner
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.common.BannerAds
import com.dieski.weski.presentation.core.designsystem.button.scroll.ScrollFloatButton
import com.dieski.weski.presentation.core.designsystem.component.LoadingIndicator
import com.dieski.weski.presentation.core.designsystem.discover.DiscoverCardWithWeatherCarousel
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.snowflake.WindBlownSnowflakeEffectBackground
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.HOME_BOTTOM_BANNER_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.HOME_FAVORITES_BANNER_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.collectWithLifecycle
import com.dieski.weski.presentation.core.util.debounceClickable
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.home.model.HomeSkiResortInfo
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import com.dieski.weski.presentation.util.log
import com.google.android.gms.ads.AdSize
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
	val context = LocalContext.current
	val state by viewModel.uiState.collectAsStateWithLifecycle()
	val coroutineScope = rememberCoroutineScope()
	val lazyListState = rememberLazyListState()
	var showBookmarkPopup by remember { mutableStateOf(false) }

	viewModel.effects.collectWithLifecycle {
		when (it) {
			is HomeEffect.NavigateToDetail -> navigateToDetail(it.resortWeatherInfo)
			is HomeEffect.ShowSnackBar -> onShowSnackBar(it.message, it.action)
			is HomeEffect.ScrollToTop -> {
				coroutineScope.launch {
					lazyListState.animateScrollToItem(0)
				}
			}
			HomeEffect.ShowServiceInfoReport -> {
				val url = "https://lizzie00.notion.site/weski"
				val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
				context.startActivity(intent)
			}
			HomeEffect.ReportBug -> {
				val url = "https://joey.team/block?block_id=oPWTFUezsWA61tdpDRoD&id=SoD0lftsYVQBUT65Hcpgp9mIBzj2"
				val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
				context.startActivity(intent)
			}

			HomeEffect.WriteFeedbackReport -> {
				val url = "https://play.google.com/store/apps/details?id=com.dieski.weski"
				val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
				context.startActivity(intent)
			}

			HomeEffect.OpenBookmarkPopup -> {
				showBookmarkPopup = true
			}
		}
	}

	LaunchedEffect(Unit) {
		viewModel.handleEvent(HomeEvent.FetchingHomeData)
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		WindBlownSnowflakeEffectBackground()

		HomeScreen(
			state = state,
			onAction = viewModel::handleEvent,
			lazyListState = lazyListState,
			showBookmarkPopup = showBookmarkPopup,
			onDismissBookmarkPopup = { showBookmarkPopup = false },
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
	showBookmarkPopup: Boolean = false,
	onDismissBookmarkPopup: () -> Unit = {}
) {
	val isTop by remember { derivedStateOf { lazyListState.canScrollBackward } }
	var reportIconOffset by remember { mutableStateOf(Offset.Zero) }
	var showReportPopup by remember { mutableStateOf(false)}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.noRippleClickable { showReportPopup = true }
	) {
		Column(
			modifier = modifier
				.fillMaxSize()
				.noRippleClickable { showReportPopup = false }
		) {
			WeskiHeader(
				endIcon = {
					Icon(
						modifier = Modifier
							.padding(end = 8.dp)
							.size(32.dp)
							.noRippleClickable { showReportPopup = true }
							.onGloballyPositioned { layoutCoordinates ->
								reportIconOffset = layoutCoordinates.boundsInRoot().bottomCenter
							},
						painter = painterResource(id = R.drawable.ic_home_report),
						tint = WeskiColor.Gray90,
						contentDescription = "홈 옵션 열기"
					)
				}
			)

			Spacer(modifier = Modifier.height(19.dp))

			Box(
				modifier = Modifier.fillMaxSize()
			) {
				HomeContent(
					resortWeatherInfoList = state.resortWeatherInfoList,
					onCardClick = { onAction(HomeEvent.ClickCard(it)) },
					onClickBookmark = { resortId, bookmarked -> onAction(HomeEvent.ToggleBookmark(resortId, bookmarked)) },
					modifier = Modifier.fillMaxSize(),
					lazyListState = lazyListState
				)

				if (state.isLoading) {
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

		ReportPopup(
			isShowing = showReportPopup,
			offset = reportIconOffset,
			onReportAction = {
				when(it) {
					ReportPopupItem.SHOW_SERVICE_INFO -> onAction(HomeEvent.ClickShowServiceInfoReport)
					ReportPopupItem.WRITE_FEEDBACK -> onAction(HomeEvent.ClickWriteFeedbackReport)
					ReportPopupItem.REPORT_BUG -> onAction(HomeEvent.ClickReportBug)
				}
			}
		)

		if (showBookmarkPopup) {
			BookmarkPopup(
				onDismiss = onDismissBookmarkPopup,
				modifier = Modifier
			)
		}
	}
}

@Composable
private fun BookmarkPopup(
	onDismiss: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier.fillMaxSize()
			.background(WeskiColor.Gray100.copy(alpha = 0.5f))
			.noRippleClickable { onDismiss() }
	) {
		Column(
			modifier = Modifier.widthIn(max = 316.dp)
				.background(color = WeskiColor.White, shape = RoundedCornerShape(20.dp))
				.align(Alignment.Center)
				.padding(top = 38.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
		) {
			Text(
				text = "이 스키장을 즐겨찾기 했어요!",
				style = WeskiTheme.typography.title2SemiBold,
				color = WeskiColor.Gray100,
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = "선택한 스키장을 상단에서 고정해서 매일 확인해볼 수 있어요",
				style = WeskiTheme.typography.body1Regular,
				color = WeskiColor.Gray60,
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(18.dp))

			BannerAds(
				modifier = Modifier
					.fillMaxWidth()
					.height(AdSize.BANNER.height.dp),
				bannerAdUnitId = HOME_FAVORITES_BANNER_AD_UNIT_ID
			)

			Spacer(Modifier.height(18.dp))

			Text(
				modifier = Modifier.fillMaxWidth()
					.background(shape = RoundedCornerShape(8.dp), color = WeskiColor.Main01)
					.noRippleClickable { onDismiss() }
					.padding(vertical = 16.5.dp),
				text = "확인",
				style = WeskiTheme.typography.title3SemiBold,
				color = WeskiColor.White,
				textAlign = TextAlign.Center
			)
		}
	}
}

enum class ReportPopupItem(
	@DrawableRes val iconRes: Int,
	val text: String
) {
	SHOW_SERVICE_INFO(
		iconRes = R.drawable.icn_report_show_service_info,
		text = "서비스 소개보기"
	),
	WRITE_FEEDBACK(
		iconRes = R.drawable.icn_report_edit,
		text = "피드백 남기기"
	),
	REPORT_BUG(
		iconRes = R.drawable.icn_report_mail,
		text = "버그 제보하기"
	)
}

@Composable
private fun ReportPopup(
	isShowing: Boolean,
	offset: Offset,
	onReportAction: (ReportPopupItem) -> Unit = {},
) {
	val density = LocalDensity.current
	var boxWidthPx by remember { mutableStateOf(0) }

	if (isShowing) {
		Column(
			modifier = Modifier
				.absoluteOffset {
					IntOffset(offset.x.toInt()-boxWidthPx, offset.y.toInt()+8)
				}
				.background(color = Color.White, shape = RoundedCornerShape(10.dp))
				.padding(vertical = 6.dp, horizontal = 4.dp)
				.onGloballyPositioned { layoutCoordinates ->
					// 박스의 너비를 픽셀 단위로 가져오기
					boxWidthPx = layoutCoordinates.size.width
				},
			verticalArrangement = Arrangement.spacedBy(2.dp)
		) {
			ReportPopupItem.entries.forEach {
				ReportPopupItem(
					iconRes = it.iconRes,
					text = it.text,
					widthDp = with(density) { boxWidthPx.toDp() },
					onItemClick = { onReportAction(it) },
				)
			}
		}
	}
}

@Composable
private fun ReportPopupItem(
	@DrawableRes iconRes: Int,
	text: String,
	widthDp: Dp,
	onItemClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.widthIn(min = widthDp)
			.background(color = Color.White, shape = RoundedCornerShape(20.dp))
			.debounceClickable { onItemClick() }
			.padding(vertical = 6.dp, horizontal = 6.dp),
		horizontalArrangement = Arrangement.spacedBy(4.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			painter = painterResource(id = iconRes),
			contentDescription = null,
			modifier = Modifier.size(15.dp),
		)

		Text(
			text = text,
			style = WeskiTheme.typography.body3Medium,
			color = WeskiColor.Gray70
		)
	}
}

@Composable
internal fun HomeContent(
	resortWeatherInfoList: List<HomeSkiResortInfo>,
	modifier: Modifier = Modifier,
	onCardClick: (HomeSkiResortInfo) -> Unit = {},
	onClickBookmark: (Long, Boolean) -> Unit = {_, _ -> },
	lazyListState: LazyListState = rememberLazyListState()
) {
	val logger = LocalLoggerOwner.current

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
					status = resortWeatherInfo.getResortOperatingStatus(),
					isBookmarked = resortWeatherInfo.isBookmarked,
					onClickCard = {
						logger.log("home_click_details", resortWeatherInfo.name)
						onCardClick(resortWeatherInfo)
					},
					onClickBookmark = {
						onClickBookmark(resortWeatherInfo.id, resortWeatherInfo.isBookmarked)
					},
					logWeatherCarouselScrolling = { isScrolling ->
						if (isScrolling) {
							logger.log("home_card_swipe", resortWeatherInfo.name)
						}
					}
				)
			}
		}

		item {
			BannerAds(
				modifier = Modifier
					.fillMaxWidth()
					.height(AdSize.BANNER.height.dp),
				bannerAdUnitId = HOME_BOTTOM_BANNER_AD_UNIT_ID
			)
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
			operatingSlopeCount = 5,
			openingDate = "2024-08-18",
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