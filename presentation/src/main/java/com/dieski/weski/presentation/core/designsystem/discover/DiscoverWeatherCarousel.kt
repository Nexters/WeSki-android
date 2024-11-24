package com.dieski.weski.presentation.core.designsystem.discover

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.LocalLoggerOwner
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.util.log
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
	weekWeatherInfoList: ImmutableList<DailyWeather>,
	modifier: Modifier = Modifier,
	logRowScrolling: (Boolean) -> Unit = {},
) {
	val lazyRowState = rememberLazyListState()
	val isScrolling by remember { derivedStateOf { lazyRowState.isScrollInProgress } }

	LaunchedEffect(isScrolling) {
		logRowScrolling(isScrolling)
	}

	LazyRow(
		modifier = modifier.fillMaxWidth(),
		state = lazyRowState
	) {
		itemsIndexed(
			weekWeatherInfoList
		) { index, weatherInfo ->
			key(weatherInfo.day) {
				DiscoverWeather(
					day = doDayOfWeek(index)+"요일",
					weatherCondition = weatherInfo.condition,
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
	val dailyWeatherLists = listOf(
		DailyWeather(day = "월요일", condition = WeatherCondition.SNOW, maxTemperature = 2, minTemperature = -7),
		DailyWeather(day ="화요일", condition = WeatherCondition.SNOW, maxTemperature = 0,  minTemperature = -7),
		DailyWeather(day ="수요일", condition = WeatherCondition.SNOW, maxTemperature = -5,  minTemperature = -7),
		DailyWeather(day ="목요일", condition = WeatherCondition.SNOW, maxTemperature = -5,  minTemperature = -7),
		DailyWeather(day ="금요일", condition = WeatherCondition.SNOW, maxTemperature = 5,  minTemperature = -7),
		DailyWeather(day ="일요일", condition = WeatherCondition.SNOW, maxTemperature = 6,  minTemperature = -7)
	)

	DiscoverWeatherCarousel(
		dailyWeatherLists.toPersistentList()
	)
}