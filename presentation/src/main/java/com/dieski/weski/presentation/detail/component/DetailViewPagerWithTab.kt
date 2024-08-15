package com.dieski.weski.presentation.detail.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.detail.congestion.CongestionScreen
import com.dieski.weski.presentation.detail.weather.WeatherScreen
import com.dieski.weski.presentation.detail.webcam.WebcamScreen
import kotlinx.coroutines.launch

data class TabItem(
    val text: String
)

@Composable
internal fun DetailViewPagerWithTab(
    modifier: Modifier = Modifier,
    onShowSnackBar: (message: String, code: String?) -> Unit = { _, _ ->}
) {
    val density = LocalDensity.current
    var pagerFirstHeight by remember { mutableStateOf(0.dp) }

    val tabItemList = listOf(
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
        modifier = modifier.fillMaxWidth()
            .onSizeChanged {
                pagerFirstHeight = with(density) { it.height.toDp() }
            }
    ) {
        DetailFeedTab(
            modifier = Modifier.fillMaxWidth(),
            tabs = tabItemList,
            currentPage = pagerState.currentPage,
            tabIdx = pagerState.currentPage,
            onTabClick = { index ->
                coroutineScope.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )

        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            beyondViewportPageCount = tabItemList.size
        ) { page ->
            when (page) {
                0 -> WebcamScreen(
                    isCurrentPage = pagerState.currentPage == 0,
                    onShowSnackBar = onShowSnackBar
                )
                1 -> WeatherScreen(
                    onShowSnackBar = onShowSnackBar
                )
                2 -> CongestionScreen(
                    isCurrentPage = pagerState.currentPage == 2,
                    onShowSnackBar = onShowSnackBar
                )
            }
        }
    }

}