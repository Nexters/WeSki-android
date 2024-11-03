package com.dieski.weski.presentation.detail.common

import android.webkit.JavascriptInterface

/**
 *
 * @author   JGeun
 * @created  2024/11/03
 */
class WebViewAppInterface(
	private val onShowToast: (String) -> Unit
) {

	@JavascriptInterface
	fun showToast(message: String) {
		onShowToast(message)
	}
}