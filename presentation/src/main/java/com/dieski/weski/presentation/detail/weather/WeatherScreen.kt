package com.dieski.weski.presentation.detail.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.designsystem.weather.WeatherDay
import com.dieski.weski.presentation.core.designsystem.weather.WeatherWeek
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
internal fun WeatherScreen(
	modifier: Modifier = Modifier,
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.background(WeskiColor.White)
			.verticalScroll(rememberScrollState())
	) {
		WeatherScreenWeatherInfoTextBox()

		Spacer(modifier = Modifier.height(18.dp))

		Spacer(
			modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
                .background(WeskiColor.Gray30)
		)

		Spacer(modifier = Modifier.height(26.dp))

		WeatherDay()

		Spacer(modifier = Modifier.height(40.dp))

        WeatherDividerLine()

        Column(
            modifier = Modifier.fillMaxWidth()
				.padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 40.dp),
			verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
			Text(
				text = "주간 예보",
				style = WeskiTheme.typography.title3SemiBold,
				color = WeskiColor.Gray90
			)

            WeatherWeek()
        }

        WeatherDividerLine()

        DetailSnowQualitySurvey(
			onShowSnackBar = onShowSnackBar
		)
	}
}

@Composable
private fun WeatherDividerLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(WeskiColor.Gray20)
    )
}

@Composable
private fun WeatherScreenWeatherInfoTextBox() {
	Column(
		modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 23.dp)
	) {
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "실시간 날씨",
			style = WeskiTheme.typography.title3SemiBold,
			color = WeskiColor.Gray90
		)

		Spacer(modifier = Modifier.height(24.dp))

		Text(
			text = "25°",
			style = WeskiTheme.typography.heading1SemiBold,
			color = WeskiColor.Gray100
		)

		Text(
			text = "체감 10°",
			style = WeskiTheme.typography.body1Regular,
			color = WeskiColor.Gray60
		)

		Spacer(modifier = Modifier.height(20.dp))

		Text(
			text = "비오고 다소 추운 날씨에요",
			style = WeskiTheme.typography.heading3SemiBold,
			color = WeskiColor.Gray90
		)

		Spacer(modifier = Modifier.height(6.dp))

		Text(
			text = "최고 28° 최저 24°",
			style = WeskiTheme.typography.heading3SemiBold,
			color = WeskiColor.Gray60
		)
	}
}

@DevicePreviews
@ThemePreviews
@Composable
private fun WeatherScreenPreview() {
	WeatherScreen()
}