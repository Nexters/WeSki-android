package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
	paddingValues: PaddingValues = PaddingValues(top = 34.dp, bottom = 34.dp, start = 30.dp, end = 24.dp)
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
			.padding(paddingValues),
		verticalArrangement = Arrangement.spacedBy(6.dp)
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.weight(1f),
				text = resortName,
				style = WeskiTheme.typography.title1Bold,
				color = WeskiColor.Gray90
			)

			Spacer(modifier = Modifier.width(8.dp))

			Image(
				modifier = Modifier.size(32.dp),
				painter = painterResource(id = weatherType.getIcon()),
				contentDescription = "Weather Icon"
			)
			
			Spacer(modifier = Modifier.width(8.dp))

			Text(
				text = "${currentTemperature}°",
				style = WeskiTheme.typography.heading15SemiBold,
				color = WeskiColor.Gray100
			)
		}

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(end = 6.25.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				modifier = Modifier.weight(1f),
				text = "개장일이 곧 공개될 예정이에요",
				style = WeskiTheme.typography.body1Medium,
				color = WeskiColor.Gray60
			)
			
			Spacer(modifier = Modifier.width(8.dp))

			Text(
				text = weatherDescription,
				style = WeskiTheme.typography.body1SemiBold,
				color = WeskiColor.Gray60,
			)
		}
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

