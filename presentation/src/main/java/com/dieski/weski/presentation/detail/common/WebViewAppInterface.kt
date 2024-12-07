package com.dieski.weski.presentation.detail.common

import android.webkit.JavascriptInterface

/**
 *
 * @author   JGeun
 * @created  2024/11/03
 */
class WebViewAppInterface(
	private val onShowToast: (String) -> Unit,
	private val setHeight: (Int) -> Unit = {}
) {

	@JavascriptInterface
	fun showToast(message: String) {
		onShowToast(message)
	}

	@JavascriptInterface
	fun setHeight(height: String) {
		setHeight(height.toInt())
	}
}