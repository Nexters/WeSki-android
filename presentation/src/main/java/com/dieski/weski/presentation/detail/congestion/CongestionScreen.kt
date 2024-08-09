package com.dieski.weski.presentation.detail.congestion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.detail.component.WeskiWebView

@Composable
internal fun CongestionScreen(
    modifier: Modifier = Modifier,
    isCurrentPage: Boolean = false
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Text(text = "혼잡도 그래프")
            }

            WeskiWebView(
                webViewUrl = "https://www.naver.com",
                startRenderingNow = isCurrentPage
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Text(text = "혼잡도 그래프")
            }
        }
    }
}