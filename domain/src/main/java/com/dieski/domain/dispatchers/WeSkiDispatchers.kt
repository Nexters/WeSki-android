package com.dieski.domain.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val weSkiDispatcher: WeSkiDispatchers)

enum class WeSkiDispatchers {
    DEFAULT,
    IO
}