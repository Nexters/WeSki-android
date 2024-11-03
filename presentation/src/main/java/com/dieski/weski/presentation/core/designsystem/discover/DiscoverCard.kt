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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.asWeatherIcon
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverCard(
	resortName: String,
	status: String,
	weatherCondition: WeatherCondition,
	currentTemperature: Int,
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp,
	paddingValues: PaddingValues = PaddingValues(top = 34.dp, bottom = 34.dp, start = 30.dp, end = 24.dp)
) {
	val glassMorphismBgColor = Brush.linearGradient(listOf(Color(0xE6FFFFFF), Color(0x99FFFFFF)))

	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(brush = glassMorphismBgColor, shape = RoundedCornerShape(cornerDp))
			.padding(paddingValues)
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
				painter = painterResource(id = weatherCondition.asWeatherIcon()),
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
				text = status,
				style = WeskiTheme.typography.body1Medium,
				color = WeskiColor.Gray60
			)
			
			Spacer(modifier = Modifier.width(8.dp))

			Text(
				text = weatherCondition.korean,
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
		currentTemperature = 7,
		weatherCondition = WeatherCondition.SNOW,
		status = "운영 중"
	)
}

