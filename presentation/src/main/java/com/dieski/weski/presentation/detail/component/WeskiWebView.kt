package com.dieski.weski.presentation.detail.component

import android.graphics.Color
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dieski.weski.presentation.detail.common.WebViewAppInterface
import timber.log.Timber

sealed interface WebViewAction {
	data class ShowToast(
		val message: String
	) : WebViewAction

	data class GetHeight(
		val height: Int
	): WebViewAction

	data class GetWebViewUrl(val url: String) : WebViewAction
}

@Composable
fun WeskiWebView(
    modifier: Modifier = Modifier,
    webViewUrl: String,
	prevWebView: WebView? = null,
    onPageFinished: () -> Unit = {},
	onAction: (WebViewAction) -> Unit = {},
	updateWebView: (WebView) -> Unit = {}
) {
	val density = LocalDensity.current
	val context = LocalContext.current
	var webViewHeight by remember { mutableIntStateOf(0) }
	val webViewAppInterface by lazy {
		WebViewAppInterface(
			onShowToast = { onAction(WebViewAction.ShowToast(it)) },
			onSetHeight = { webViewHeight = it },
			onOpenUrl = { onAction(WebViewAction.GetWebViewUrl(it)) }
		)
	}

	val webView = remember {
		prevWebView ?: WebView(context).apply {
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
				webViewClient = object : WebViewClient() {}
			}
	}

	LaunchedEffect (webView) {
		updateWebView(webView)
	}

	Box(
		modifier = modifier.fillMaxWidth()
			.onSizeChanged {
				val height = with(density) {
					it.height.toDp()
				}
				if (height != 0.dp) {
					onPageFinished()
				}
			}
	) {
		AndroidView(
			modifier = Modifier
				.fillMaxWidth(),
//			.height(webViewHeight), // 높이를 동적으로 업데이트
			factory = { webView },
			update = { view ->
				if (view.url != webViewUrl) {
					Timber.d("WeskiWebView", "update - webViewUrl: $webViewUrl")
					view.loadUrl(webViewUrl)
				}
			}
		)
	}
}