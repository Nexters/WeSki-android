package com.dieski.weski.presentation.detail.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
        containerColor = Color.LightGray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabIdx]),
                color = Color.Red
            )
        }
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier.width(tabWidth),
                text = {
                    Text(
                        modifier = Modifier,
                        text = item.text,
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
    Color.Red
} else {
    Color.LightGray
}

private fun isCurrentTab(currentPage: Int, tabIdx: Int) = currentPage == tabIdx
