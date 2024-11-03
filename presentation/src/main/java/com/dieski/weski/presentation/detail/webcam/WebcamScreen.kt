package com.dieski.weski.presentation.detail.webcam

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WeskiWebView

@Composable
internal fun WebcamScreen(
	modifier: Modifier = Modifier,
	state: DetailState = DetailState(),
	submitSnowQualitySurvey: (isLike: Boolean) -> Unit,
	isCurrentPage: Boolean = false,
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
    var isWebViewFinished by remember { mutableStateOf(false) }

	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			Log.e("Test@@@", "WebcamScreen: ${state.webcamWebUrl}")
			WeskiWebView(
				modifier = Modifier
					.fillMaxWidth()
					.background(Color.Transparent),
				webViewUrl = state.webcamWebUrl,
				startRenderingNow = isCurrentPage,
				onShowSnackBar = onShowSnackBar,
				onPageFinished = {
					isWebViewFinished = true
				}
			)

			if (isWebViewFinished) {
				DetailSnowQualitySurvey(
					totalNum = state.snowQualitySurveyResult.totalVotes,
					likeNum = state.snowQualitySurveyResult.positiveVotes,
					onSubmit = { isGood ->
						submitSnowQualitySurvey(isGood)
					},
					onShowSnackBar = onShowSnackBar
				)
			}
		}
	}
}