package com.dieski.weski.presentation.detail.component

import android.graphics.Bitmap
import android.graphics.Color
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dieski.weski.presentation.detail.common.WebViewAppInterface

@Composable
fun WeskiWebView(
    modifier: Modifier = Modifier,
    webViewUrl: String,
    startRenderingNow: Boolean = false,
	onShowSnackBar: (String, String?) -> Unit = { _, _ -> },
    onPageFinished: () -> Unit = {},
) {
	var webViewHeight by remember { mutableIntStateOf(0) }

	val webViewAppInterface by lazy {
		WebViewAppInterface(
			onShowToast = { onShowSnackBar(it, null) },
			setHeight = { webViewHeight = it }
		)
	}

    // Adds view to Compose
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
			.height(webViewHeight.dp),
        factory = { context ->
            WebView(context).apply {
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                settings.javaScriptEnabled = true
				settings.setSupportMultipleWindows(true)
				settings.javaScriptCanOpenWindowsAutomatically = true
				setLayerType(WebView.LAYER_TYPE_NONE, null)
                setBackgroundColor(Color.TRANSPARENT)
				settings.mediaPlaybackRequiresUserGesture = false
				addJavascriptInterface(webViewAppInterface, "Android")
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
						super.onPageFinished(view, url)
					}

					override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
						super.doUpdateVisitedHistory(view, url, isReload)
					}
				}
            }
        },
        update = { view ->
			view.loadUrl(webViewUrl)
        },
    )
}