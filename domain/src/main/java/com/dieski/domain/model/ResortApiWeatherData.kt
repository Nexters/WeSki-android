package com.dieski.domain.model

import kotlin.random.Random

//enum class ResortApiWeatherData(
//    val key: Int,
//    val todayForecast: TodayForecast,
//    val weekForecast: List<WeekWeatherInfo>,
//) {
//    JISAN(
//        key = 1,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.JISAN.temperature,
//            perceivedTemperature = ResortApiData.JISAN.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.JISAN.weatherDescription,
//            highestTemperature = ResortApiData.JISAN.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.JISAN.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.JISAN.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.JISAN.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    GONJIAM(
//        key = 2,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.GONJIAM.temperature,
//            perceivedTemperature = ResortApiData.GONJIAM.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.GONJIAM.weatherDescription,
//            highestTemperature = ResortApiData.GONJIAM.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.GONJIAM.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.GONJIAM.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.GONJIAM.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    VIVALDIPARK(
//        key = 3,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.VIVALDIPARK.temperature,
//            perceivedTemperature = ResortApiData.VIVALDIPARK.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.VIVALDIPARK.weatherDescription,
//            highestTemperature = ResortApiData.VIVALDIPARK.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.VIVALDIPARK.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.VIVALDIPARK.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.VIVALDIPARK.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    ELYSIAN_GANGCHON(
//        key = 4,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.ELYSIAN_GANGCHON.temperature,
//            perceivedTemperature = ResortApiData.ELYSIAN_GANGCHON.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.ELYSIAN_GANGCHON.weatherDescription,
//            highestTemperature = ResortApiData.ELYSIAN_GANGCHON.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.ELYSIAN_GANGCHON.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.ELYSIAN_GANGCHON.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.ELYSIAN_GANGCHON.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    PHOENIX(
//        key = 6,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.PHOENIX.temperature,
//            perceivedTemperature = ResortApiData.PHOENIX.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.PHOENIX.weatherDescription,
//            highestTemperature = ResortApiData.PHOENIX.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.PHOENIX.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.PHOENIX.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.PHOENIX.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    YONGPYONG(
//        key = 8,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.YONGPYONG.temperature,
//            perceivedTemperature = ResortApiData.YONGPYONG.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.YONGPYONG.weatherDescription,
//            highestTemperature = ResortApiData.YONGPYONG.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.YONGPYONG.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.YONGPYONG.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.JISAN.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    EDEN(
//        key = 10,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.EDEN.temperature,
//            perceivedTemperature = ResortApiData.EDEN.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.EDEN.weatherDescription,
//            highestTemperature = ResortApiData.EDEN.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.EDEN.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.EDEN.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.EDEN.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    WELLIHILLI(
//        key = 5,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.WELLIHILLI.temperature,
//            perceivedTemperature = ResortApiData.WELLIHILLI.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.WELLIHILLI.weatherDescription,
//            highestTemperature = ResortApiData.WELLIHILLI.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.WELLIHILLI.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.WELLIHILLI.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(0, 80)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.WELLIHILLI.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(0, 80),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    ),
//    MUJU(
//        key = 9,
//        todayForecast = TodayForecast(
//            currentTemperature = ResortApiData.MUJU.temperature,
//            perceivedTemperature = ResortApiData.MUJU.temperature + Random.nextInt(1, 6),
//            weatherDescription = ResortApiData.MUJU.weatherDescription,
//            highestTemperature = ResortApiData.MUJU.dailyWeather[0].maxTemperature,
//            lowestTemperature = ResortApiData.MUJU.dailyWeather[0].minTemperature,
//            hourlyForecastWeatherInfoList = ResortApiData.MUJU.dailyWeather.map {
//                TodayForecast.HourlyForecastWeatherInfo(
//                    time = "",
//                    weatherType = it.weatherType,
//                    temperature = it.maxTemperature,
//                    chanceOfRain = Random.nextInt(20, 60)
//                )
//            }
//        ),
//        weekForecast = ResortApiData.MUJU.dailyWeather.map {
//            WeekWeatherInfo(
//                day = "",
//                date = "",
//                weatherType = it.weatherType,
//                chanceOfRain = Random.nextInt(20, 60),
//                highestTemperature = it.maxTemperature,
//                lowestTemperature = it.minTemperature
//            )
//        }
//    )
//}