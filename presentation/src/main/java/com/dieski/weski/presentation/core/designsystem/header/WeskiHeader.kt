package com.dieski.weski.presentation.core.designsystem.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun WeskiHeader(
	modifier: Modifier = Modifier,
	title: String? = null,
	startIcon: (@Composable () -> Unit)? = null,
	endIcon: (@Composable () -> Unit)? = null,
	bgColor: Color = Color.Transparent,
) {
	Box(
		modifier = modifier
            .fillMaxWidth()
            .background(bgColor)
            .padding(vertical = 14.dp, horizontal = 12.dp)
	) {
		if (startIcon != null) {
			Box(
				modifier = Modifier
					.align(Alignment.CenterStart)
			) {
				startIcon()
			}
		}

		if (title == null) {
			Icon(
				modifier = Modifier
                    .align(Alignment.Center)
                    .width(67.dp)
                    .height(21.dp),
				painter = painterResource(id = R.drawable.ic_weski_header),
				contentDescription = "WeSki Logo"
			)
		} else {
			Text(
				modifier = Modifier.align(Alignment.Center),
				text = title,
				color = WeskiColor.Gray100,
				textAlign = TextAlign.Center,
				style = WeskiTheme.typography.title3Bold
			)
		}

		if (endIcon != null) {
			Box(
				modifier = Modifier
					.align(Alignment.CenterEnd)
			) {
				endIcon()
			}
		}
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun HeaderPreview() {
	WeskiTheme {
		Column {
			WeskiHeader(
				title = null,
				startIcon = {
					Icon(
						modifier = Modifier
							.size(26.dp),
						painter = painterResource(id = R.drawable.ic_arrow_back),
						tint = Color.Black,
						contentDescription = "뒤로가기"
					)
				}
			)
			WeskiHeader(
				title = "지산리조트",
				endIcon = {
					Icon(
						modifier = Modifier
							.size(26.dp),
						painter = painterResource(id = R.drawable.icn_share),
						tint = WeskiColor.Gray90,
						contentDescription = "공유하기"
					)
				}
			)
		}
	}
}