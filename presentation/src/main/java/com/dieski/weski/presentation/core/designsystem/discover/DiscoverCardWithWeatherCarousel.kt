package com.dieski.weski.presentation.core.designsystem.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.domain.model.SkiResortInfo.DailyWeather
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.asWeatherIcon
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceClickable
import com.dieski.weski.presentation.core.util.debounceNoRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme
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
	isBookmarked: Boolean,
	onClickCard: () -> Unit,
	onClickBookmark: () -> Unit,
	modifier: Modifier = Modifier,
	bgColor: Color = WeskiColor.Gray10,
	cornerDp: Dp = 15.dp,
	logWeatherCarouselScrolling: (Boolean) -> Unit = {}
) {
	val glassMorphismBgColor = Brush.linearGradient(listOf(Color(0xE6FFFFFF), Color(0x99FFFFFF)))

	Column(
		modifier = modifier
			.background(brush = glassMorphismBgColor, shape = RoundedCornerShape(cornerDp))
			.debounceClickable { onClickCard() }
			.padding(top = 12.dp, bottom = 16.dp)
	) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.padding(PaddingValues(top = 23.dp, bottom = 23.dp, start = 30.dp,  end = 24.dp)),
			verticalArrangement = Arrangement.spacedBy(6.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Row(
					modifier = Modifier.weight(1f),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy(4.dp)
				) {
					Text(
						text = resortName,
						style = WeskiTheme.typography.title1Bold,
						color = WeskiColor.Gray90
					)

					val bookmarkIcon = if (isBookmarked) {
						R.drawable.ic_resort_bookmark_filled
					} else {
						R.drawable.ic_resort_bookmark
					}

					Image(
						modifier = Modifier.size(20.dp)
							.debounceNoRippleClickable { onClickBookmark() },
						painter = painterResource(bookmarkIcon),
						contentDescription = "Bookmark Icon"
					)
				}

				Spacer(modifier = Modifier.width(8.dp))

				Image(
					modifier = Modifier.size(32.dp),
					painter = painterResource(id = weatherCondition.asWeatherIcon()),
					contentDescription = "Weather Icon"
				)

				Spacer(modifier = Modifier.width(8.dp))

				Text(
					text = "${currentTemperature}°",
					style = WeskiTheme.typography.heading15SemiBold,
					color = WeskiColor.Gray100
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
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
					text = weatherCondition.korean,
					style = WeskiTheme.typography.body1SemiBold,
					color = WeskiColor.Gray60,
				)
			}
		}
		
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
		operatingSlopeCount = 5,
		currentTemperature = 7,
		weatherCondition = WeatherCondition.SNOW,
		weekWeatherInfoList = dailyWeatherLists,
		status = "운영 중",
		isBookmarked = false,
		onClickCard = {},
		onClickBookmark = {}
	)
}