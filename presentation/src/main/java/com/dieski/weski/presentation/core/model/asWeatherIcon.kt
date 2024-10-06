package com.dieski.weski.presentation.core.model

import androidx.annotation.DrawableRes
import com.dieski.domain.model.WeatherCondition
import com.dieski.weski.presentation.R

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
@DrawableRes
fun WeatherCondition.asWeatherIcon(
	isNight:Boolean = false
): Int {
	return when(this) {
		WeatherCondition.SUNNY -> if(isNight) {
			R.drawable.icn_day_night
		} else {
			R.drawable.icn_day_noon
		}
		WeatherCondition.CLOUDY -> if(isNight) {
			R.drawable.icn_night_cloudy
		} else {
			R.drawable.icn_noon_cloudy
		}
		WeatherCondition.OVERCAST_RAIN -> R.drawable.icn_rain
		WeatherCondition.RAIN -> R.drawable.icn_rain
		WeatherCondition.SNOW -> R.drawable.icn_snow
		WeatherCondition.FOG -> if(isNight) {
			R.drawable.icn_night_cloudy
		} else {
			R.drawable.icn_noon_cloudy
		}
	}
}