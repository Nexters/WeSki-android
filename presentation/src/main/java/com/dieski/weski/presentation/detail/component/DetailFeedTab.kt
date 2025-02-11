package com.dieski.weski.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.detail.model.TabItem
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
internal fun DetailFeedTab(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
    currentPage: Int,
    tabIdx: Int,
    onTabClick: (index: Int) -> Unit
) {
    val density = LocalDensity.current
    var tabWidth by remember { mutableStateOf(0.dp) }

    ScrollableTabRow(
        modifier = modifier
            .onGloballyPositioned {
                tabWidth = with(density) {
                    it.size.width.toDp() / tabs.size
                }
            },
        selectedTabIndex = currentPage,
        edgePadding = 0.dp,
        containerColor = WeskiColor.White,
        indicator = { tabPositions ->
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabIdx]),
                height = 2.dp,
                color = WeskiColor.Gray90
            )
        },
        divider = {
            HorizontalDivider(
                thickness = 2.dp,
                color = WeskiColor.Gray20
            )
        }
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier.width(tabWidth)
                    .background(WeskiColor.White),
                text = {
                    Text(
                        modifier = Modifier,
                        text = item.text,
                        style = WeskiTheme.typography.title3SemiBold,
                        color = getTabTextColor(tabIdx, index),
                        textAlign = TextAlign.Center
                    )
                },
                selected = tabIdx == index,
                onClick = { onTabClick(index) }
            )
        }
    }
}

private fun getTabTextColor(
    currentPage: Int,
    tabIdx: Int
) = if (isCurrentTab(currentPage, tabIdx)) {
    WeskiColor.Gray90
} else {
    WeskiColor.Gray40
}

private fun isCurrentTab(currentPage: Int, tabIdx: Int) = currentPage == tabIdx
