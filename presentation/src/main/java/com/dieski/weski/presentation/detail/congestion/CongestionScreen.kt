package com.dieski.weski.presentation.detail.congestion

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
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WeskiWebView
import com.dieski.weski.presentation.detail.congestion.component.CongestionGraph
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
internal fun CongestionScreen(
	modifier: Modifier = Modifier,
	state: DetailState = DetailState(),
	isCurrentPage: Boolean = false,
	isWebViewActive: Boolean = true,
	submitSnowQualitySurvey: (isLike: Boolean) -> Unit = {},
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
	var isWebViewFinished by remember { mutableStateOf(false) }

	Box(
		modifier = modifier
			.fillMaxSize()
			.background(WeskiColor.White)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(WeskiColor.White)
		) {
			CongestionGraph()

			Spacer(
				modifier = Modifier
					.fillMaxWidth()
					.height(6.dp)
					.background(WeskiColor.Gray20)
			)

			if (!isWebViewFinished) {
				Spacer(
					modifier = Modifier
						.fillMaxSize()
						.height(300.dp)
						.background(WeskiColor.White)
				)
			}

			WeskiWebView(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 32.dp, horizontal = 5.dp),
				webViewUrl = state.slopeWebUrl,
				startRenderingNow = isCurrentPage,
				onPageFinished = {
					isWebViewFinished = true
				}
			)

			if (isWebViewFinished)  {
				Spacer(
					modifier = Modifier
						.fillMaxWidth()
						.height(6.dp)
						.background(WeskiColor.Gray20)
				)

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

@DevicePreviews
@ThemePreviews
@Composable
private fun CongestionScreenPreview() {
	WeskiTheme {
		CongestionScreen(isCurrentPage = true)
	}
}