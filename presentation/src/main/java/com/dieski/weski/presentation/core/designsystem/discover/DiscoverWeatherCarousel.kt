package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.ResortWeatherInfo.ResortDailyWeatherInfo
import com.dieski.weski.presentation.core.model.WeatherType
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import java.util.Calendar

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
		itemsIndexed(
			weekWeatherInfoList
		) { index, weatherInfo ->
			key(weatherInfo.day) {
				DiscoverWeather(
					day = doDayOfWeek(index)+"요일",
					weatherType = WeatherType.findByName(weatherInfo.weatherType),
					avgTemperature = weatherInfo.maxTemperature,
					minTemperature = weatherInfo.minTemperature,
					isToday = index == 0
				)
			}
		}
	}
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

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverWeatherCarouselPreview() {
	val resortDailyWeatherInfoList = listOf(
		ResortDailyWeatherInfo(day = "월요일", weatherType = "normal", maxTemperature = 2, minTemperature = -7),
		ResortDailyWeatherInfo(day ="화요일", weatherType = "snow", maxTemperature = 0,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="수요일", weatherType = "cloudy", maxTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="목요일", weatherType = "rain", maxTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="금요일", weatherType = "normal", maxTemperature = 5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="일요일", weatherType = "normal", maxTemperature = 6,  minTemperature = -7)
	)

	DiscoverWeatherCarousel(
		resortDailyWeatherInfoList.toPersistentList()
	)
}