package com.dieski.weski.presentation.core.designsystem.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun WeatherWeek(
	modifier: Modifier = Modifier,
	day: String,
	date: String,
	weatherCondition: WeatherCondition,
	chanceOfRain: String,
	highestTemperature: Int,
	lowestTemperature: Int
) {
	Row(
		modifier = modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = day,
				style = WeskiTheme.typography.body1SemiBold,
				color = WeskiColor.Gray80
			)

			Spacer(modifier = Modifier.width(5.dp))

			Text(
				text = date,
				style = WeskiTheme.typography.body3Regular,
				color = WeskiColor.Gray50
			)
		}

		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				modifier = Modifier.size(24.dp),
				painter = painterResource(id = weatherCondition.asWeatherIcon()),
				contentDescription = "weather image"
			)

			Spacer(modifier = Modifier.width(12.dp))

			Column(
				modifier = Modifier.width(30.dp)
			) {
				Text(
					text = "강수",
					style = WeskiTheme.typography.body3Regular,
					color = WeskiColor.Gray60
				)
				Text(
					text = chanceOfRain,
					style = WeskiTheme.typography.body2SemiBold,
					color = WeskiColor.Gray70
				)
			}

			Spacer(modifier = Modifier.width(21.dp))

			Row(
				modifier = Modifier.width(80.dp)
			) {
				Text(
					text = "${highestTemperature}°",
					style = WeskiTheme.typography.title2Regular,
					color = WeskiColor.Gray70
				)

				Spacer(modifier = Modifier.width(5.dp))

				Text(
					text = "/",
					style = WeskiTheme.typography.title2Regular,
					color = WeskiColor.Gray50
				)

				Spacer(modifier = Modifier.width(3.dp))

				Text(
					text = "${lowestTemperature}°",
					style = WeskiTheme.typography.title2SemiBold,
					color = WeskiColor.Main01
				)
			}

		}
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun WeatherWeekPreview() {
	WeatherWeek(
		day = "월요일",
		date = "8.25",
		weatherCondition = WeatherCondition.SNOW,
		chanceOfRain = "0%",
		highestTemperature = 10,
		lowestTemperature = -2
	)
}