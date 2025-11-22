package com.dieski.weski.presentation.detail.webcam

import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.common.BannerAds
import com.dieski.weski.presentation.core.common.ExoPlayerView
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DETAIL_WEBCAM_BANNER1_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.DETAIL_WEBCAM_BOTTOM_BANNER_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WebViewAction
import com.dieski.weski.presentation.detail.component.WeskiWebView
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import kotlinx.coroutines.delay

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
	var showPlayerUrl: String by rememberSaveable { mutableStateOf("") }
	var remainingSeconds by rememberSaveable { mutableIntStateOf(30) }

	LaunchedEffect(showPlayerUrl) {
		if (showPlayerUrl.isNotEmpty()) {
			remainingSeconds = 30
			while (remainingSeconds > 0) {
				delay(1000L)
				remainingSeconds--
			}
			showPlayerUrl = ""
		}
	}

	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			Box(
				modifier = Modifier.fillMaxWidth()
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
							is WebViewAction.ShowVideoUrl -> {
								showPlayerUrl = action.url
							}
						}
					},
					updateWebView = updateWebcamWebView
				)

				if (showPlayerUrl.isNotEmpty()) {
					Box(
						modifier = Modifier.fillMaxWidth()
							.aspectRatio(16f/9f)
					) {
						ExoPlayerView(
							videoUri = showPlayerUrl,
							modifier = Modifier.fillMaxWidth()
								.aspectRatio(16f/9f)
								.align(Alignment.TopCenter)
						)

						Box(
							modifier = Modifier.fillMaxWidth()
								.background(color = WeskiColor.Gray100.copy(alpha = 0.5f))
								.align(Alignment.BottomCenter)
								.padding(vertical = 9.dp, horizontal = 12.dp)
						) {
							Text(
								text = "웹캠 화면은 ${remainingSeconds}초 후 자동으로 닫힙니다.",
								modifier = Modifier.align(Alignment.Center),
								style = WeskiTheme.typography.body1Medium,
								color = WeskiColor.Gray10
							)
						}

						Box(
							modifier = Modifier.align(Alignment.TopEnd)
								.padding(top = 16.dp, end = 16.dp)
								.noRippleClickable {
									showPlayerUrl = ""
								}
								.background(
									color = Color(0xFF070707).copy(alpha = 0.5f),
									shape = RoundedCornerShape(8.dp)
								)
								.padding(all = 6.dp)
						) {
							Icon(
								modifier = Modifier.align(Alignment.Center),
								painter = painterResource(R.drawable.ico_player_close),
								contentDescription = "웹캠 닫기",
								tint = WeskiColor.White
							)
						}
					}
				}
			}

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