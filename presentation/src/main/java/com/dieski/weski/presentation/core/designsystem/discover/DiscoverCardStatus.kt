package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.ui.theme.WeskiTheme

/**
 * @author   JGeun
 * @created  2025/10/08
 */
@Composable
internal fun DiscoverCardStatus(
	status: String,
	weatherConditionText: String
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(start = 30.dp, end = 24.dp)
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
			text = weatherConditionText,
			style = WeskiTheme.typography.body1SemiBold,
			color = WeskiColor.Gray60,
		)
	}
}

@Preview
@Composable
private fun DiscoverCardStatusPreview() {
	WeskiTheme {
		DiscoverCardStatus(
			status = "운영 중",
			weatherConditionText = WeatherCondition.OVERCAST_RAIN.korean
		)
	}
}