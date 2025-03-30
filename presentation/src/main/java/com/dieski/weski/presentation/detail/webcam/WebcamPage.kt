package com.dieski.weski.presentation.detail.webcam

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.common.BannerAds
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DETAIL_WEBCAM_BANNER1_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.DETAIL_WEBCAM_BOTTOM_BANNER_AD_UNIT_ID
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WebViewAction
import com.dieski.weski.presentation.detail.component.WeskiWebView

@Composable
internal fun WebcamPage(
	modifier: Modifier = Modifier,
	state: DetailState,
	webView: WebView? = null,
	submitSnowQualitySurvey: (isLike: Boolean) -> Unit,
	navigateToWebView: (url: String) -> Unit = {},
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> },
	updateWebcamWebView: (WebView) -> Unit
) {
    var isWebViewFinished by remember { mutableStateOf(false) }

	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			WeskiWebView(
				modifier = Modifier
					.fillMaxWidth()
					.background(Color.Transparent),
				webViewUrl = state.webcamWebUrl,
				prevWebView = webView,
				onPageFinished = {
					isWebViewFinished = true
				},
				onAction = { action ->
					when(action) {
						is WebViewAction.ShowToast -> onShowSnackBar(action.message, null)
						is WebViewAction.GetHeight -> {}
						is WebViewAction.GetWebViewUrl -> {
							navigateToWebView(action.url)
						}
					}
				},
				updateWebView = updateWebcamWebView
			)

			if (isWebViewFinished) {
				BannerAds(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 28.dp, vertical = 7.dp),
					bannerAdUnitId = DETAIL_WEBCAM_BANNER1_AD_UNIT_ID
				)

				Spacer(
					modifier = Modifier
						.fillMaxWidth()
						.height(6.dp)
						.background(WeskiColor.Gray20)
				)

				DetailSnowQualitySurvey(
					totalNum = state.totalResortSnowQualitySurvey.totalVotes,
					likeNum = state.totalResortSnowQualitySurvey.positiveVotes,
					onSubmit = { isGood ->
						submitSnowQualitySurvey(isGood)
					},
					onShowSnackBar = onShowSnackBar
				)

				BannerAds(
					modifier = Modifier
						.fillMaxWidth(),
					bannerAdUnitId = DETAIL_WEBCAM_BOTTOM_BANNER_AD_UNIT_ID
				)
			}
		}
	}
}