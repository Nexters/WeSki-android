package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.WeatherType
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
	resortName: String,
	operatingSlopeCount: Int,
	weatherType: WeatherType,
	currentTemperature: Int,
	weatherDescription: String,
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp,
	paddingValues: PaddingValues = PaddingValues(top = 34.dp, bottom = 35.dp, start = 30.dp, end = 24.dp)
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
			.padding(paddingValues)
	) {
		Column(
			modifier = Modifier
				.weight(1f),
			verticalArrangement = Arrangement.spacedBy(5.dp)
		) {
			Text(
				text = resortName,
				style = WeskiTheme.typography.title1Bold,
				color = WeskiColor.Gray90
			)

			Text(
				text = "운행중인 슬로프 ${operatingSlopeCount}개",
				style = WeskiTheme.typography.body1Medium,
				color = WeskiColor.Gray60
			)
		}

		Column(
			modifier = Modifier,
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalAlignment = Alignment.End
		) {
			DiscoverTemperatureWithIcon(
				temperature = currentTemperature,
				weatherType = weatherType
			)

			Text(
				modifier = Modifier,
				text = weatherDescription,
				style = WeskiTheme.typography.body1SemiBold,
				color = WeskiColor.Gray60,
				textAlign = TextAlign.End
			)
		}
	}
}

@Composable
private fun DiscoverTemperatureWithIcon(
	temperature: Int,
	weatherType: WeatherType,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Image(
			modifier = Modifier.size(37.dp),
			painter = painterResource(id = weatherType.getIcon()),
			contentDescription = "Weather Icon"
		)

		Text(
			text = "${temperature}°",
			style = WeskiTheme.typography.heading1SemiBold,
			color = WeskiColor.Gray100
		)
	}
}


@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverCardPreview() {
	DiscoverCard(
		resortName = "용평스키장 모나",
		operatingSlopeCount = 5,
		currentTemperature = 7,
		weatherType = WeatherType.SNOW,
		weatherDescription = "흐리고 눈"
	)
}

