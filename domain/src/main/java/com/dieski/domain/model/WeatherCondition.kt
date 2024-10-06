package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
enum class WeatherCondition(
	val korean: String
) {
	SUNNY(
		korean = "맑음"
	),
	CLOUDY(
		korean = "흐림"
	),
	OVERCAST_RAIN(
		korean = "흐리고 비"
	),
	RAIN(
		korean = "비"
	),
	SNOW(
		korean = "눈"
	),
	FOG(
		korean = "안개"
	);

	companion object {
		fun findByKorean(condition: String) =
			entries.firstOrNull { it.korean == condition } ?: SUNNY

		fun findByName(name: String) = entries.firstOrNull { it.name.equals(name, true) } ?: SUNNY
	}
}