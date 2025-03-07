package com.dieski.weski.presentation.detail.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.common.BannerAds
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.designsystem.weather.WeatherTime
import com.dieski.weski.presentation.core.designsystem.weather.WeatherWeek
import com.dieski.weski.presentation.core.util.DETAIL_WEATHER_BANNER1_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.DETAIL_WEATHER_BOTTOM_BANNER_AD_UNIT_ID
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.DetailState
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
internal fun WeatherScreen(
	modifier: Modifier = Modifier,
	state: DetailState = DetailState(),
	submitSnowQualitySurvey: (isLike: Boolean) -> Unit = {},
	onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> },
	logWeatherTimeRowScrolling: (Boolean) -> Unit = {}
) {
	val weatherTimeLazyRowListState = rememberLazyListState()
	val isScrolling by remember { derivedStateOf { weatherTimeLazyRowListState.isScrollInProgress } }

	LaunchedEffect(isScrolling) {
		logWeatherTimeRowScrolling(isScrolling)
	}

	Column(
		modifier = modifier
			.fillMaxSize()
			.background(WeskiColor.White)
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

		LazyRow(
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 24.dp),
			state = weatherTimeLazyRowListState
		) {
			itemsIndexed(
				state.todayWeatherByTime
			) { index, info ->
				WeatherTime(
					time = getTime(index),
					weatherCondition = WeatherCondition.SNOW,
					temperature = info.temperature,
					chanceOfRain = info.precipitationChance
				)
				
				if (index != state.todayWeatherByTime.lastIndex) {
					Spacer(modifier = Modifier.width(24.dp))
				}
			}
		}

		Spacer(modifier = Modifier.height(40.dp))

		WeatherDividerLine()

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 40.dp)
		) {
			Text(
				text = "주간 예보",
				style = WeskiTheme.typography.title3SemiBold,
				color = WeskiColor.Gray90
			)
			
			Spacer(modifier = Modifier.height(24.dp))

			state.weeklyWeather.forEachIndexed { index, info ->
				WeatherWeek(
					day = if(index == 0) "오늘" else doDayOfWeek(index)+"요일",
					date = getWeek(index),
					weatherCondition = WeatherCondition.SNOW,
					chanceOfRain = info.precipitationChance,
					highestTemperature = info.maxTemperature,
					lowestTemperature = info.minTemperature,
					showRainChanceText = index == 0
				)

				if (index != state.weeklyWeather.lastIndex) {
					Spacer(modifier = Modifier.height(6.dp))
					Spacer(
						modifier = Modifier
							.fillMaxWidth()
							.height(1.dp)
							.background(
								WeskiColor.Gray30.copy(
									alpha = 0.8f
								)
							)
					)
					Spacer(modifier = Modifier.height(6.dp))
				}
			}
		}

		WeatherDividerLine()

		BannerAds(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 28.dp, vertical = 17.dp),
			bannerAdUnitId = DETAIL_WEATHER_BANNER1_AD_UNIT_ID
		)

		DetailSnowQualitySurvey(
			totalNum = state.totalResortSnowQualitySurvey.totalVotes,
			likeNum = state.totalResortSnowQualitySurvey.positiveVotes,
			onSubmit = { submitSnowQualitySurvey(it) },
			onShowSnackBar = onShowSnackBar
		)

		BannerAds(
			modifier = Modifier
				.fillMaxWidth(),
			bannerAdUnitId = DETAIL_WEATHER_BOTTOM_BANNER_AD_UNIT_ID
		)
	}
}

private fun getTime(index: Int): String {
	val current = LocalDateTime.now().plusHours(index * 2L)
	val formatter = DateTimeFormatter.ofPattern("HH")
	return current.format(formatter)
}

private fun getWeek(index: Int): String {
	val current = LocalDateTime.now().plusDays(index.toLong())
	val formatter = DateTimeFormatter.ofPattern("MM.dd")
	return current.format(formatter)
}

private fun doDayOfWeek(index: Int): String? {
	val cal: Calendar = Calendar.getInstance()
	var strWeek: String? = null
	var nWeek: Int = ((cal.get(Calendar.DAY_OF_WEEK) + index) % 8)
	if (nWeek == 0) nWeek += 1

	if (nWeek == 1) {
		strWeek = "일"
	} else if (nWeek == 2) {
		strWeek = "월"
	} else if (nWeek == 3) {
		strWeek = "화"
	} else if (nWeek == 4) {
		strWeek = "수"
	} else if (nWeek == 5) {
		strWeek = "목"
	} else if (nWeek == 6) {
		strWeek = "금"
	} else if (nWeek == 7) {
		strWeek = "토"
	}
	return strWeek
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
			style = WeskiTheme.typography.body1Regular,
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