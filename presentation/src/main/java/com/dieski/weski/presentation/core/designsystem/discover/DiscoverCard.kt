package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
// TODO 배경색 추후 수정필요 - 디자인 확정 시
@Composable
fun DiscoverCard(
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
			.padding(start = 30.dp, end = 24.dp)
	) {
		Column(
			modifier = Modifier
				.weight(1f)
				.padding(top = 4.dp, bottom = 8.dp),
			verticalArrangement = Arrangement.spacedBy(5.dp)
		) {
			Text(
				text = "스키장 명",
				style = WeskiTheme.typography.title1Bold,
				color = WeskiColor.Gray90
			)

			Text(
				text = "운행중인 슬로프 -개",
				style = WeskiTheme.typography.body1Medium,
				color = WeskiColor.Gray60
			)
		}

		Column(
			modifier = Modifier
				.padding(top = 4.dp, bottom = 8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.End
		) {
			DiscoverTemperatureWithIcon()

			Text(
				modifier = Modifier,
				text = "흐리고 비",
				style = WeskiTheme.typography.body1SemiBold,
				color = WeskiColor.Gray60,
				textAlign = TextAlign.End
			)
		}
	}
}

@Composable
private fun DiscoverTemperatureWithIcon(
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Image(
			modifier = Modifier.size(37.dp),
			painter = painterResource(id = R.drawable.ic_launcher_background),
			contentDescription = "Weather Icon"
		)

		Text(
			text = "25°",
			style = WeskiTheme.typography.heading1SemiBold,
			color = WeskiColor.Gray100
		)
	}
}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverTemperatureWithIconPreview() {
	DiscoverTemperatureWithIcon()
}


@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverCardPreview() {
	DiscoverCard()
}

