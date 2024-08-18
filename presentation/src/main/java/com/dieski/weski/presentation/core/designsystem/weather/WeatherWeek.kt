package com.dieski.weski.presentation.core.designsystem.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
@Composable
fun WeatherWeek(
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = "오늘",
			style = WeskiTheme.typography.body1SemiBold,
			color = WeskiColor.Gray80
		)

		Spacer(modifier = Modifier.width(5.dp))

		Text(
			text = "7.26",
			style = WeskiTheme.typography.body3Regular,
			color = WeskiColor.Gray50
		)

		Spacer(Modifier.weight(1f))

		Icon(
			painter = painterResource(id = R.drawable.icn_day_night),
			contentDescription = "weather_icon"
		)

		Spacer(modifier = Modifier.width(12.dp))

		Column {
			Text(
				text = "강수",
				style = WeskiTheme.typography.body3Regular,
				color = WeskiColor.Gray60
			)
			Text(
				text = "50%",
				style = WeskiTheme.typography.body2SemiBold,
				color = WeskiColor.Gray70
			)
		}

		Spacer(modifier = Modifier.width(21.dp))

		Text(
			text = "3°",
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
			text = "-2°",
			style = WeskiTheme.typography.title2SemiBold,
			color = WeskiColor.Main01
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun WeatherWeekPreview() {
	WeatherWeek()
}