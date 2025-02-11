package com.dieski.weski.presentation.detail.web

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.header.WeskiHeader
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.detail.component.WeskiWebView
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2024/12/22
 */
@Composable
fun WebcamConnectRouter(
	resortId: Long,
	resortName: String,
	url: String,
	padding: PaddingValues,
	onNavigateUp: () -> Unit,
) {
	WebcamConnectScreen(
		resortId = resortId,
		resortName = resortName,
		url = url,
		modifier = Modifier
			.fillMaxSize()
			.background(WeskiColor.White)
			.padding(padding),
		onNavigateUp = onNavigateUp
	)
}

@Composable
fun WebcamConnectScreen(
	resortId: Long,
	resortName: String,
	url: String,
	modifier: Modifier = Modifier,
	onNavigateUp: () -> Unit = {}
) {
	var isWebcamShow: Boolean by rememberSaveable { mutableStateOf(false) }

	LaunchedEffect(Unit) {
		launch(Dispatchers.IO) {
			delay(2000L)
			isWebcamShow = true
		}
	}

	Column(
		modifier = modifier
			.fillMaxSize()
			.background(Color(0xFFF0F3F6))
	) {
		WeskiHeader(
			title = resortName,
			bgColor = WeskiColor.White,
			startIcon = {
				Icon(
					modifier = Modifier
						.size(26.dp)
						.noRippleClickable { onNavigateUp() },
					painter = painterResource(id = R.drawable.ic_arrow_back),
					tint = Color.Black,
					contentDescription = "뒤로가기"
				)
			},
		)

		if (isWebcamShow) {
			WeskiWebView(
				webViewUrl = url,
				modifier = Modifier
					.fillMaxSize()
			)
		} else {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			){
				Image(
					modifier = Modifier.width(86.dp).height(53.dp),
					painter = painterResource(R.drawable.img_webcam_connect),
					contentDescription = "webcam connect",
				)

				Spacer(modifier = Modifier.height(38.dp))

				Text(
					text = "웹캠 정보를 불러오고 있어요",
					color = WeskiColor.Gray100,
					textAlign = TextAlign.Center,
					style = WeskiTheme.typography.title1SemiBold
				)

				Spacer(modifier = Modifier.height(10.dp))

				Text(
					text = "선택하신 스키장의 웹캠 정보는\n공식 홈페이지에서 제공돼요",
					color = WeskiColor.Gray60,
					textAlign = TextAlign.Center,
					style = WeskiTheme.typography.body1Regular
				)
			}
		}
	}
}

@DevicePreviews
@Composable
private fun WebcamConnectScreenPreview() {
	WebcamConnectScreen(
		resortId = 1,
		resortName = "지산리조트",
		url = ""
	)
}