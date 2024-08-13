package com.dieski.weski.presentation.core.model

import com.dieski.weski.presentation.R
import javax.annotation.concurrent.Immutable

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */

@Immutable
enum class WeatherType {
	SNOW, RAIN, CLOUDY, NORMAL;

	fun getIcon() = when(this) {
		SNOW -> R.drawable.icn_snow
		RAIN -> R.drawable.icn_rain
		CLOUDY -> R.drawable.icn_noon_cloudy
		NORMAL -> R.drawable.icn_day_noon
	}

	companion object {
		fun findByName(name: String) = entries.firstOrNull { it.name.equals(name, true) } ?: NORMAL
	}
}