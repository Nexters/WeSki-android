package com.dieski.weski.presentation.detail.common

import android.webkit.JavascriptInterface

/**
 *
 * @author   JGeun
 * @created  2024/11/03
 */

class WebViewAppInterface(
	private val onShowToast: (String) -> Unit,
	private val onSetHeight: (Int) -> Unit = {},
	private val onOpenUrl: (String) -> Unit = {}
) {

	@JavascriptInterface
	fun showToast(message: String) {
		onShowToast(message)
	}

	@JavascriptInterface
	fun setHeight(height: String) {
		onSetHeight(height.toInt())
	}

	@JavascriptInterface
	fun openUrl(url: String) {
		onOpenUrl(url)
	}
}