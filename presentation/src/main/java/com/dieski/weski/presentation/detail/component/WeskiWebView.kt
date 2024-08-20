package com.dieski.weski.presentation.detail.component

import android.graphics.Color
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WeskiWebView(
    modifier: Modifier = Modifier,
    webViewUrl: String,
    startRenderingNow: Boolean = false
) {
    var viewRenderingComplete by remember { mutableStateOf(false) }

    // webViewUrl 변경에 따른 rendering 초기화
    LaunchedEffect(webViewUrl) {
        viewRenderingComplete = false
    }

    // WebView가 보여지는 타이밍에 한번만 로드되도록 처리
    LaunchedEffect(startRenderingNow) {
        if (!viewRenderingComplete && startRenderingNow) {
            viewRenderingComplete = startRenderingNow
        }
    }

    // Adds view to Compose
    AndroidView(
        modifier = modifier
            .fillMaxWidth(), // Occupy the max size in the Compose UI tree
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                settings.javaScriptEnabled = true
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setBackgroundColor(Color.TRANSPARENT)
//                    webViewClient = WebViewClient()
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                    }
                }
            }
        },
        update = { view ->
            if (viewRenderingComplete) {
                view.loadUrl(webViewUrl)
            }
        }
    )
}