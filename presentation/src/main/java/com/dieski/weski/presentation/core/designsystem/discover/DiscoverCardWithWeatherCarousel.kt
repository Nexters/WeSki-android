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
	operatingSlopeCount: Int,
	weatherCondition: WeatherCondition,
	status: String,
	currentTemperature: Int,
	weekWeatherInfoList: ImmutableList<DailyWeather>,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp
) {
	Column(
		modifier = modifier
			.background(color = bgColor, shape = RoundedCornerShape(cornerDp))
			.debounceClickable { onClick() }
			.padding(top = 12.dp, bottom = 16.dp)
	) {
		DiscoverCard(
			resortName = resortName,
			operatingSlopeCount = operatingSlopeCount,
			weatherCondition = weatherCondition,
			currentTemperature = currentTemperature,
			bgColor = bgColor,
			cornerDp = cornerDp,
			status = status,
			paddingValues = PaddingValues(top = 23.dp, bottom = 23.dp, start = 30.dp,  end = 24.dp)
		)
		
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.dp)
				.padding(horizontal = 24.dp)
				.background(
					WeskiColor.Gray80.copy(alpha = 0.04f)
				)
		)

		Spacer(modifier = Modifier.height(14.dp))

		DiscoverWeatherCarousel(
			weekWeatherInfoList =weekWeatherInfoList,
			modifier = Modifier.padding(start = 14.dp)
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
		operatingSlopeCount = 5,
		currentTemperature = 7,
		weatherCondition = WeatherCondition.SNOW,
		weekWeatherInfoList = dailyWeatherLists,
		status = "운영 중",
		onClick = {}
	)
}