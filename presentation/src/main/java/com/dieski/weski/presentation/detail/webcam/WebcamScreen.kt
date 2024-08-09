package com.dieski.weski.presentation.detail.webcam

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.dieski.weski.presentation.detail.component.WeskiWebView

@Composable
internal fun WebcamScreen(
    modifier: Modifier = Modifier,
    isCurrentPage: Boolean = false,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Log.e("Test@@@", "WebcamScreen")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            WeskiWebView(
                webViewUrl = "https://www.naver.com",
                startRenderingNow = isCurrentPage
            )
        }
    }
}