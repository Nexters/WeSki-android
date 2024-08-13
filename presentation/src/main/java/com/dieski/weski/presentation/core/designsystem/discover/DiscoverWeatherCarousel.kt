package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.dieski.domain.model.ResortDailyWeatherInfo
import com.dieski.weski.presentation.core.model.WeatherType
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverWeatherCarousel(
	weekWeatherInfoList: ImmutableList<ResortDailyWeatherInfo>,
	modifier: Modifier = Modifier
) {

	LazyRow(
		modifier = modifier.fillMaxWidth()
	) {
		items(
			weekWeatherInfoList
		) { weatherInfo ->
			key(weatherInfo.day) {
				DiscoverWeather(
					day = weatherInfo.day,
					weatherType = WeatherType.findByName(weatherInfo.weatherType),
					avgTemperature = weatherInfo.avgTemperature,
					minTemperature = weatherInfo.minTemperature,
					isToday = weatherInfo.day == "화요일")
			}
		}
	}
}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverWeatherCarouselPreview() {
	val resortDailyWeatherInfoList = listOf(
		ResortDailyWeatherInfo(day = "월요일",  weatherType = "normal", avgTemperature = 2,  minTemperature =  -7),
		ResortDailyWeatherInfo(day ="화요일", weatherType = "snow", avgTemperature = 0,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="수요일", weatherType = "cloudy", avgTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="목요일", weatherType = "rain", avgTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="금요일", weatherType = "normal", avgTemperature = 5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="일요일", weatherType = "normal", avgTemperature = 6,  minTemperature = -7)
	)

	DiscoverWeatherCarousel(
		resortDailyWeatherInfoList.toPersistentList()
	)
}