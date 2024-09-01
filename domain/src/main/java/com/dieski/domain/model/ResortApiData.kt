package com.dieski.domain.model

import kotlin.random.Random
import kotlin.random.nextInt

/**
 *
 * @author   JGeun
 * @created  2024/08/20
 */
enum class ResortApiData(
	val resortName: String,
	val key: Int,
	val webKey: String,
	val weatherType: String,
	val weatherDescription: String,
	val temperature: Int,
	val resortDailyWeatherInfo:  List<ResortWeatherInfo.ResortDailyWeatherInfo>
) {
	JISAN(
		resortName = "지산리조트",
		key = 1,
		webKey = "jisan",
		weatherType = "CLOUDY",
		weatherDescription = "흐림",
		temperature = 31,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(31, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	GONJIAM(
		resortName = "곤지암스키장",
		key = 2,
		webKey = "gonjiam",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(33, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	VIVALDIPARK(
		resortName = "비발디파크",
		key = 3,
		webKey = "vivaldipark",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 30,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	ELYSIAN_GANGCHON(
		resortName = "엘리시안 강촌",
		key = 4,
		webKey = "elysian-gangchon",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(33, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	PHOENIX(
		resortName = "휘닉스파크",
		key = 6,
		webKey = "phoenix",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	YONGPYONG(
		resortName = "용평리조트 모나",
		key = 8,
		webKey = "yongpyong",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(33, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	EDEN(
		resortName = "에덴벨리",
		key = 10,
		webKey = "eden",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(33, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	WELLIHILLI(
		resortName = "웰리힐리",
		key = 5,
		webKey = "wellihilli",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 31,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(31, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	),
	MUJU(
		resortName = "무주리조트 덕유산",
		key = 9,
		webKey = "muju",
		weatherType = "NORMAL",
		weatherDescription = "맑음",
		temperature = 33,
		resortDailyWeatherInfo = listOf(
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "CLOUDY",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "RAIN",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			),
			ResortWeatherInfo.ResortDailyWeatherInfo(
				day = "",
				weatherType = "NORMAL",
				maxTemperature = Random.nextInt(30, 35),
				minTemperature = Random.nextInt(26, 29)
			)
		)
	)
}