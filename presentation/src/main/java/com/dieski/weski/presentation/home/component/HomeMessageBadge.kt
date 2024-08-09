package com.dieski.weski.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeMessageBadge(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .padding(paddingValues)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "이번 주 날씨를 살펴보고 방문할 스키장을 탐색해보세요"
        )
    }
}