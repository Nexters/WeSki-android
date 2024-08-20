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
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.ResortWeatherInfo.ResortDailyWeatherInfo
import com.dieski.weski.presentation.core.model.WeatherType
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceClickable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun DiscoverCardWithWeatherCarousel(
	resortName: String,
	operatingSlopeCount: Int,
	weatherType: WeatherType,
	currentTemperature: Int,
	weatherDescription: String,
	weekWeatherInfoList: ImmutableList<ResortDailyWeatherInfo>,
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
			weatherType = weatherType,
			currentTemperature = currentTemperature,
			weatherDescription = weatherDescription,
			bgColor = bgColor,
			cornerDp = cornerDp,
			paddingValues = PaddingValues(top = 23.dp, bottom = 23.dp, start = 30.dp,  end = 24.dp)
		)
		
		Spacer(modifier = Modifier.height(1.dp))

		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.dp)
				.padding(horizontal = 24.dp)
				.background(WeskiColor.Gray80)
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
	val resortDailyWeatherInfoList = listOf(
		ResortDailyWeatherInfo(day = "월요일", weatherType = "normal", maxTemperature = 2, minTemperature = -7),
		ResortDailyWeatherInfo(day ="화요일", weatherType = "snow", maxTemperature = 0,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="수요일", weatherType = "cloudy", maxTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="목요일", weatherType = "rain", maxTemperature = -5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="금요일", weatherType = "normal", maxTemperature = 5,  minTemperature = -7),
		ResortDailyWeatherInfo(day ="일요일", weatherType = "normal", maxTemperature = 6,  minTemperature = -7)
	)

	DiscoverCardWithWeatherCarousel(
		resortName = "용평스키장 모나",
		operatingSlopeCount = 5,
		currentTemperature = 7,
		weatherType = WeatherType.SNOW,
		weatherDescription = "흐리고 눈",
		weekWeatherInfoList = resortDailyWeatherInfoList.toPersistentList(),
		onClick = {}
	)
}