package com.dieski.data.dispatchers

import androidx.core.location.LocationRequestCompat.Quality
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val weSkiDispatcher: WeSkiDispatchers)

enum class WeSkiDispatchers {
    DEFAULT,
    IO
}