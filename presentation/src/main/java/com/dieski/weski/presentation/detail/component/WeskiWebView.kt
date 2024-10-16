package com.dieski.weski.presentation.detail.component

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WeskiWebView(
    modifier: Modifier = Modifier,
    webViewUrl: String,
    startRenderingNow: Boolean = false,
    onPageFinished: () -> Unit = {},
) {
    var viewRenderingComplete by remember { mutableStateOf(false) }
    var loadingFinished by remember { mutableStateOf(false) }

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
            .fillMaxWidth(),
        factory = { context ->
            WebView(context).apply {
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                settings.javaScriptEnabled = true
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                setBackgroundColor(Color.TRANSPARENT)
                webViewClient = object : WebViewClient() {
                    override fun onPageCommitVisible(view: WebView?, url: String?) {
                        super.onPageCommitVisible(view, url)
                        onPageFinished()
                    }

					override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
						super.onScaleChanged(view, oldScale, newScale)
					}

					override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
						super.onPageStarted(view, url, favicon)
					}

					override fun onPageFinished(view: WebView?, url: String?) {
						if (url != webViewUrl) {
							super.onPageFinished(view, url)
						}
					}

					override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
						if (url != webViewUrl) {
							super.doUpdateVisitedHistory(view, url, isReload)
						}
					}
				}
            }
        },
        update = { view ->
            if (viewRenderingComplete) {
				view.loadUrl(webViewUrl)
            }
        },
    )
}