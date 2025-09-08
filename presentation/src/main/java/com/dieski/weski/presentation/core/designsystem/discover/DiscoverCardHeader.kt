package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.asWeatherIcon
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceNoRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

/**
 * @author   JGeun
 * @created  2025/10/08
 */
@Composable
internal fun DiscoverCardHeader(
	resortName: String,
	isBookmarked: Boolean,
	weatherCondition: WeatherCondition,
	currentTemperature: Int,
	onClickBookmark: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.fillMaxWidth()
			.padding(start = 30.dp, end = 24.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Row(
			modifier = Modifier.weight(1f),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(4.dp)
		) {
			Text(
				text = resortName,
				style = WeskiTheme.typography.title1Bold,
				color = WeskiColor.Gray90
			)

			val bookmarkIcon = if (isBookmarked) {
				R.drawable.ic_resort_bookmark_filled
			} else {
				R.drawable.ic_resort_bookmark
			}

			Image(
				modifier = Modifier.size(20.dp)
					.debounceNoRippleClickable { onClickBookmark() },
				painter = painterResource(bookmarkIcon),
				contentDescription = "Bookmark Icon"
			)
		}

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
}

@Preview
@Composable
private fun DiscoverCardHeaderPreview() {
	WeskiTheme {
		DiscoverCardHeader(
			resortName = "용평스키장 모나",
			isBookmarked = true,
			weatherCondition = WeatherCondition.SNOW,
			currentTemperature = 7,
			onClickBookmark = {}
		)
	}
}