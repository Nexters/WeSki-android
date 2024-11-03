package com.dieski.weski.presentation.core.designsystem.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
fun WeatherTime(
	modifier: Modifier = Modifier,
	time: String,
	weatherCondition: WeatherCondition,
	temperature: Int,
	chanceOfRain: String
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = time,
			style = WeskiTheme.typography.body3SemiBold,
			color = WeskiColor.Gray50
		)

		Spacer(modifier = Modifier.height(13.dp))

		Image(
			modifier = Modifier.size(32.dp),
			painter = painterResource(id = weatherCondition.asWeatherIcon()),
			contentDescription = "weather image"
		)

		Spacer(modifier = Modifier.height(13.dp))

		Text(
			text = "${temperature}°",
			style = WeskiTheme.typography.title3SemiBold,
			color = WeskiColor.Gray90
		)
		Text(
			text = chanceOfRain,
			style = WeskiTheme.typography.body3Regular,
			color = WeskiColor.Gray60
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun WeatherDayPreview() {
	WeatherTime(
		time = "오전 6시",
		weatherCondition = WeatherCondition.SNOW,
		temperature = -2,
		chanceOfRain = "20%"
	)
}