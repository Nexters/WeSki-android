package com.dieski.weski.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun IntroScreen() {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(
				brush = Brush.linearGradient(
					colors = listOf(
						Color(0xFFFFFFFF),
						Color(0xFFFFFFFF),
						Color(0xFFE8EFFE),
						Color(0xFFDBE9FF)
					)
				)
			),
	) {
		Column(
			modifier = Modifier.align(Alignment.Center),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				modifier = Modifier
					.width(133.dp)
					.height(38.85.dp),
				painter = painterResource(id = R.drawable.ic_weski_logo),
				contentDescription = "logo"
			)
			
			Spacer(modifier = Modifier.height(26.dp))

			Text(
				text = "스키장 실시간 정보를 한눈에",
				style = WeskiTheme.typography.title2Regular,
				color = WeskiColor.Gray80
			)
		}
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun IntroScreenPreview() {
	IntroScreen()
}