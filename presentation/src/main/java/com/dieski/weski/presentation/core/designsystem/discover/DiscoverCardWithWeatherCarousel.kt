package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceClickable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverCardWithWeatherCarousel(
	resortName: String,
	weatherCondition: WeatherCondition,
	status: String,
	currentTemperature: Int,
	weekWeatherInfoList: ImmutableList<DailyWeather>,
	isBookmarked: Boolean,
	onClickCard: () -> Unit,
	onClickBookmark: () -> Unit,
	modifier: Modifier = Modifier,
	cornerDp: Dp =  15.dp,
	logWeatherCarouselScrolling: (Boolean) -> Unit = {}
) {
	DiscoverCard(
		resortName = resortName,
		status = status,
		weatherCondition = weatherCondition,
		currentTemperature = currentTemperature,
		isBookmarked = isBookmarked,
		onClickBookmark = onClickBookmark,
		mainContentPaddingValues = PaddingValues(top = 34.dp, bottom = 16.dp),
		cornerDp = cornerDp,
		modifier = modifier.fillMaxWidth()
			.clip(RoundedCornerShape(cornerDp))
			.debounceClickable { onClickCard() }
	) {
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			Spacer(Modifier.height(24.dp))
		}
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.dp)
				.padding(horizontal = 24.dp)
				.background(WeskiColor.Gray80.copy(alpha = 0.04f))
		)

		Spacer(modifier = Modifier.height(14.dp))

		DiscoverWeatherCarousel(
			weekWeatherInfoList =weekWeatherInfoList,
			modifier = Modifier.padding(start = 14.dp),
			logRowScrolling = logWeatherCarouselScrolling
		)
	}
}

@ThemePreviews
@DevicePreviews
@Composable
private fun DiscoverCardWithWeatherCarouselPreview() {
	val dailyWeatherLists = persistentListOf(
		DailyWeather(day = "월요일", condition = WeatherCondition.SNOW, maxTemperature = 2, minTemperature = -7),
		DailyWeather(day ="화요일", condition = WeatherCondition.SNOW, maxTemperature = 0,  minTemperature = -7),
		DailyWeather(day ="수요일", condition = WeatherCondition.SNOW, maxTemperature = -5,  minTemperature = -7),
		DailyWeather(day ="목요일", condition = WeatherCondition.SNOW, maxTemperature = -5,  minTemperature = -7),
		DailyWeather(day ="금요일", condition = WeatherCondition.SNOW, maxTemperature = 5,  minTemperature = -7),
		DailyWeather(day ="일요일", condition = WeatherCondition.SNOW, maxTemperature = 6,  minTemperature = -7)
	)

	DiscoverCardWithWeatherCarousel(
		resortName = "용평스키장 모나",
		currentTemperature = 7,
		weatherCondition = WeatherCondition.SNOW,
		weekWeatherInfoList = dailyWeatherLists,
		status = "운영 중",
		isBookmarked = false,
		onClickCard = {},
		onClickBookmark = {}
	)
}