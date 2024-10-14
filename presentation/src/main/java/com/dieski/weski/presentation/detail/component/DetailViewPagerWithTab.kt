package com.dieski.weski.presentation.detail.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dieski.domain.model.SkiResortWebKey
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.detail.DetailEvent
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.congestion.CongestionScreen
import com.dieski.weski.presentation.detail.weather.WeatherScreen
import com.dieski.weski.presentation.detail.webcam.WebcamScreen
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Immutable
data class TabItem(
    val text: String
)

@Composable
internal fun DetailViewPagerWithTab(
	state: DetailState,
	modifier: Modifier = Modifier,
	submitSnowQualitySurvey: (isLike: Boolean) -> Unit = {},
	onShowSnackBar: (message: String, code: String?) -> Unit = { _, _ ->}
) {
    val density = LocalDensity.current
    var pagerFirstHeight by remember { mutableStateOf(0.dp) }
	var tabWidth by remember { mutableStateOf(0.dp) }

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
        modifier = modifier
			.fillMaxWidth()
			.onSizeChanged {
				pagerFirstHeight = with(density) { it.height.toDp() }
				tabWidth = with(density) { it.width.toDp() / tabItemList.size }
			}
    ) {
        DetailFeedTab(
            modifier = Modifier.fillMaxWidth().zIndex(1f),
            tabs = tabItemList,
            currentPage = pagerState.currentPage,
            tabIdx = pagerState.currentPage,
            onTabClick = { index ->
                coroutineScope.launch {
                    pagerState.scrollToPage(index)
                }
            },
        )

		if (state.resortWebKey != SkiResortWebKey.NONE) {
			HorizontalPager(
				modifier = Modifier
					.fillMaxSize()
					.background(WeskiColor.White),
				state = pagerState,
//            beyondViewportPageCount = tabItemList.size,
//            verticalAlignment = Alignment.Top
			) { page ->
				when (page) {
					0 -> WebcamScreen(
						state = state,
						isCurrentPage = pagerState.currentPage == 0,
						submitSnowQualitySurvey = {
							submitSnowQualitySurvey(it)
						},
						onShowSnackBar = onShowSnackBar
					)
					1 -> WeatherScreen(
						state = state,
						submitSnowQualitySurvey = {
							submitSnowQualitySurvey(it)
						},
						onShowSnackBar = onShowSnackBar
					)
					2 -> CongestionScreen(
						state = state,
						submitSnowQualitySurvey = {
							submitSnowQualitySurvey(it)
						},
						isCurrentPage = pagerState.currentPage == 2,
						onShowSnackBar = onShowSnackBar
					)
				}
			}
		}
	}
}