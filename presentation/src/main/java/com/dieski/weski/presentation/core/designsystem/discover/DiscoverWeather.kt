package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverWeather(
	isToday: Boolean,
	modifier: Modifier = Modifier,
	defaultBgColor: Color = Color.Transparent,
	selectedBgColor:Color = WeskiColor.Main05,
	defaultBorderStrokeColor: Color = WeskiColor.Main01,
	selectedBorderStrokeColor: Color = Color.Transparent,
	cornerDp: Dp = 40.dp
) {
	val bgColor = if (isToday) selectedBgColor else defaultBgColor
	val borderColor = if (isToday) defaultBorderStrokeColor else selectedBorderStrokeColor

	Column(
		modifier = modifier
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
			.border(width = 1.dp, shape = RoundedCornerShape(cornerDp), color = borderColor)
			.padding(vertical = 17.dp, horizontal = 16.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "토요일",
			style = WeskiTheme.typography.body3Regular,
			color = WeskiColor.Gray60
		)

		Spacer(modifier = Modifier.height(7.dp))

		Image(
			modifier = Modifier.size(32.dp),
			painter = painterResource(id = R.drawable.ic_launcher_background),
			contentDescription = "weather icon"
		)

		Spacer(modifier = Modifier.height(12.dp))

		Text(
			text = "0°",
			style = WeskiTheme.typography.title3SemiBold,
			color = WeskiColor.Gray90
		)

		Text(
			text = "0°",
			style = WeskiTheme.typography.body3Regular,
			color = WeskiColor.Gray60
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun DiscoverWeatherPreview() {
	Row {
		DiscoverWeather(
			isToday = false
		)
		DiscoverWeather(
			isToday = true
		)
	}
}